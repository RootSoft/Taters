package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.node.NodeAddress;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.*;
import com.rootsoft.taters.utils.Log;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.PeerAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = AddressHandler.class.getSimpleName();

    //Attributes
    private AddressProtocol protocol;

    //Constructors

    public AddressHandler(Node node) {
        this(node, null);
    }

    public AddressHandler(Node node, ProtocolHandler next) {
        super(node, next);
    }

    @Override
    public void resolveProtocolResponse(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.ADDR)) {
            this.protocol = (AddressProtocol) protocol;
            handleResponse();
            return;
        }

        super.resolveProtocolResponse(protocol);
    }

    /**
     * We received the known peers from our host, we try to connect to them and send a ping to them.
     */
    private void handleResponse() {

        //Filter own peerId from the list
        List<NodeAddress> result = protocol.getKnownPeers().stream()
                .filter(line -> !line.getPeerId().equals(node.getPeer().peerID()))
                .collect(Collectors.toList());

        Log.i(node.getName() + " received peers: ");

        for (NodeAddress address : result) {
            Log.i("Address: " + address);
            boolean connected = false;
            try {
                PeerAddress peer = new PeerAddress(address.getPeerId(), InetAddress.getByName(address.getHostAddress()), address.getTcpPort(), address.getUdpPort());
                connected = node.connect(peer);
                if (connected)
                    node.sendProtocol(peer, new PingProtocol("Ping"));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected Protocol response() {
        return null;
    }

}
