package com.rootsoft.taters.models.node;

import com.rootsoft.taters.models.PeerNetwork;
import com.rootsoft.taters.models.node.implementations.NodeEventCallback;
import com.rootsoft.taters.models.protocols.ProtocolExecutor;
import com.rootsoft.taters.models.protocols.ProtocolSerializer;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import com.rootsoft.taters.utils.Log;
import net.tomp2p.dht.*;
import net.tomp2p.futures.*;
import net.tomp2p.p2p.PeerBuilder;
import net.tomp2p.p2p.RequestP2PConfiguration;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.Number640;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Future;

/**
 * https://tomp2p.net/doc/quick/
 */
public abstract class Node {

    //Constants
    public static final String TAG = Node.class.getSimpleName();
    static final Random RND = new Random(42L);

    //Attributes
    private String name;
    private int port;
    private boolean connected;

    private PeerNetwork peerNetwork;
    private PeerDHT peer;
    private NodeEventCallback callback;
    protected ProtocolExecutor executor;
    private ProtocolSerializer serializer;

    //Constructors

    /**
     * Create a new node.
     *
     * You can either set a random node ID, or you can create the node with a KeyPair, which takes a public key and
     * generates the ID (SHA-1) out of this key.
     */
    public Node(String name, int port) {
        this(name, port, null);
    }

    /**
     * Create a new node.
     *
     * You can either set a random node ID, or you can create the node with a KeyPair, which takes a public key and
     * generates the ID (SHA-1) out of this key.
     */
    public Node(String name, int port, NodeEventCallback callback) {
        this.name = name;
        this.port = port;
        this.callback = callback;
        this.executor = new ProtocolExecutor(this);
        this.serializer = new ProtocolSerializer();

        try {
            peer = new PeerBuilderDHT(new PeerBuilder(new Number160(RND)).ports(port).start()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        init();

    }

    private void init() {
        if (peer == null)
            return;

        peer.peer().objectDataReply((sender, request) -> {
            if (callback == null) {
                return null;
            }

            if (sender.equals(peer.peerAddress())) {
                return null;
            }

            Protocol protocol = serializer.deserialize((String) request);
            protocol.setSender(sender);
            return callback.onProtocolRequestReceived(protocol);
        });
    }

    //Methods

    /**
     * Sends a protocol message on the network.
     * Everyone can respond
     *
     * @param protocol The protocol to be sent.
     */
    public void sendProtocol(Protocol protocol) {
        FutureSend futureSend = peer.send(new Number160(new Random(42L)))
                .object(serializer.serialize(protocol))
                .start();

        futureSend.addListener(new BaseFutureAdapter<FutureSend>() {
            @Override
            public void operationComplete(FutureSend future) throws Exception {
                if (callback == null) {
                    return;
                }

                callback.onMessageSent(protocol);

                if (!future.isCompleted()) {
                    callback.onError(400, "Unable to send message");
                }

                for (Map.Entry<PeerAddress, Object> entry : future.rawDirectData2().entrySet()) {
                    if (!entry.getKey().equals(peer.peerAddress())) {
                        Protocol response = (Protocol) entry.getValue();
                        response.setSender(entry.getKey());
                        callback.onProtocolResponseReceived(response);
                    }
                }
            }
        });

    }

    /**
     * Send a direct protocol message to the given peer
     * @param address
     * @param protocol
     */
    public void sendProtocol(PeerAddress address, Protocol protocol) {
        FutureDirect futureDirect = peer.peer()
                .sendDirect(address)
                .object(serializer.serialize(protocol))
                .start();

        futureDirect.addListener(new BaseFutureAdapter<FutureDirect>() {

            @Override
            public void operationComplete(FutureDirect response) throws Exception {
                if (callback == null) {
                    return;
                }

                if (!response.isCompleted()) {
                    callback.onError(400, "Unable to send message");
                }

                Protocol message = (Protocol) response.object();
                message.setSender(address);
                callback.onProtocolResponseReceived(message);
            }
        });

    }

    public void broadcast(Protocol protocol) {
        try {
            NavigableMap<Number640, Data> dataMap = new TreeMap<>();
            dataMap.put(Number640.ZERO, new Data(protocol));
            peer.peer().broadcast(new Number160(new Random(42L))).dataMap(dataMap).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establish a connection with a peer.
     */
    public boolean connect(PeerAddress sender) {
        return getPeer().peerBean().peerMap().peerFound(sender, null, null, null);
    }


    //Properties

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getName() {
        return name;
    }

    public void setPeerNetwork(PeerNetwork peerNetwork) {
        this.peerNetwork = peerNetwork;
    }

    public PeerNetwork getPeerNetwork() {
        return peerNetwork;
    }

    public void setNodeEventCallback(NodeEventCallback callback) {
        this.callback = callback;
    }

    /**
     * Returns a list of all known peers attached to this node.
     */
    public List<PeerAddress> getKnownPeers() {
        if (peer == null)
            return new ArrayList<>();

        return peer.peerBean().peerMap().all();
    }

    /**
     * A proper shutdown is initiated by calling shutdown() from the master node.
     */
    public void shutdown() {
        if (peer == null)
            return;

        peer.shutdown().awaitUninterruptibly();
    }

    //Properties

    public PeerDHT getPeer() {
        return peer;
    }

    @Override
    public String toString() {
        return "Node{" +
                "peer=" + peer +
                '}';
    }

    public abstract boolean canMine();
    public abstract boolean canRoute();
    public abstract boolean hasCompleteBlockchain();
    public abstract boolean hasWallet();
}
