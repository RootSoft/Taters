package com.rootsoft.taters.models.node;

import com.rootsoft.taters.models.commands.ProtocolCommand;
import com.rootsoft.taters.models.protocols.ProtocolFactory;
import com.rootsoft.taters.models.protocols.ProtocolMessage;
import net.tomp2p.dht.*;
import net.tomp2p.futures.BaseFutureAdapter;
import net.tomp2p.futures.FutureBootstrap;
import net.tomp2p.p2p.PeerBuilder;
import net.tomp2p.p2p.RequestP2PConfiguration;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.rpc.ObjectDataReply;
import net.tomp2p.storage.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * https://tomp2p.net/doc/quick/
 */
public abstract class Node {

    //Constants
    public static final String TAG = Node.class.getSimpleName();
    static final Random RND = new Random(42L);

    //Attributes
    private String name;
    private boolean connected;

    private PeerDHT peer;
    private NodeEventCallback callback;

    //Constructors

    /**
     * Create a new node.
     *
     * You can either set a random node ID, or you can create the node with a KeyPair, which takes a public key and
     * generates the ID (SHA-1) out of this key.
     */
    public Node(String name, NodeEventCallback callback) {
        this.name = name;
        this.callback = callback;

        try {
            peer = new PeerBuilderDHT(new PeerBuilder(new Number160(RND)).ports(4001).start()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        init();

    }

    /**
     * Creates a new node with a masterpeer.
     *
     * @param masterNode
     */
    public Node(String name, Node masterNode, NodeEventCallback callback) {
        this.callback = callback;

        try {
            peer = new PeerBuilderDHT(new PeerBuilder(new Number160(RND)).masterPeer(masterNode.getPeer().peer()).start()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        init();

    }

    private void init() {
        if (peer == null)
            return;

        peer.peer().objectDataReply((sender, request) -> callback.onMessageReceived(sender, (ProtocolMessage) request));
    }

    //Methods

    /**
     * Sends a protocol message on the network.
     *
     * @param message A message to be sent
     */
    public void sendProtocolMessage(ProtocolMessage message) {
        FutureSend futureSend = peer.send(Number160.createHash("key"))
                .object(message) //TODO Serialize the message using GSON
                .requestP2PConfiguration(new RequestP2PConfiguration(1, 5, 0))
                .start();

        futureSend.addListener(new BaseFutureAdapter<FutureSend>() {
            @Override
            public void operationComplete(FutureSend future) throws Exception {
                callback.onMessageSent(message);

                if (!future.isCompleted()) {
                    callback.onError(400, "Unable to send message");
                }

                for (Object object : futureSend.rawDirectData2().values()) {
                    callback.onResponseReceived((ProtocolMessage) object);
                }

            }
        });

    }

    /**
     * Bootstrap the node to a given node.
     * <p>
     * A bootstrapping node, also known as a rendezvous host, is a node in an overlay network that provides initial
     * configuration information to newly joining nodes so that they may successfully join the overlay network.
     */
    public boolean bootstrap(Node bootstrappingNode) {
        if (peer == null || bootstrappingNode.getPeer() == null)
            return false;

        FutureBootstrap bootstrap = peer.peer().bootstrap().peerAddress(bootstrappingNode.getPeer().peerAddress()).start();
        bootstrap.awaitUninterruptibly();

        return true;
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

    /**
     * Returns a list of all known peers attached to this node.
     */
    public List<PeerAddress> getKnownPeers() {
        List<PeerAddress> peers = new ArrayList<>();
        if (peer == null)
            return peers;

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
}
