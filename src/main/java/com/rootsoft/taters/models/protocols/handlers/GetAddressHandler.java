package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.node.NodeAddress;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.AddressProtocol;
import com.rootsoft.taters.models.protocols.messages.GetAddressProtocol;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import com.rootsoft.taters.models.protocols.messages.VerackProtocol;
import com.rootsoft.taters.utils.Log;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;

import java.util.ArrayList;
import java.util.List;

public class GetAddressHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = GetAddressHandler.class.getSimpleName();

    //Attributes
    private GetAddressProtocol protocol;
    private List<NodeAddress> knownPeers;

    //Constructors

    public GetAddressHandler(Node node) {
        this(node, null);
    }

    public GetAddressHandler(Node node, ProtocolHandler next) {
        super(node, next);
        knownPeers = new ArrayList<>();
    }

    @Override
    public Protocol resolveProtocolRequest(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.GET_ADDR)) {
            this.protocol = (GetAddressProtocol) protocol;
            handleRequest();
            return response();
        }

        return super.resolveProtocolRequest(protocol);
    }

    /**
     * Received the request from the client,
     * map all our known peers to a list and send it back
     */
    private void handleRequest() {
        knownPeers = new ArrayList<>();
        for (PeerAddress peerAddress : node.getKnownPeers()) {
            Number160 peerId = peerAddress.peerId();
            String hostName = peerAddress.inetAddress().getHostName();
            String hostAddress = peerAddress.inetAddress().getHostAddress();
            int tcpPort = peerAddress.tcpPort();
            int udpPort = peerAddress.udpPort();
            knownPeers.add(new NodeAddress(peerId, hostName, hostAddress, tcpPort, udpPort));
        }

        Log.i(node.getName() + " knows : " + knownPeers.size() + " peers.");
    }

    private void handleResponse() {
    }

    @Override
    protected Protocol response() {
        //Log.i(node.getName() + " known peers: " + knownPeers.size());
//        for (NodeAddress address : knownPeers) {
//            Log.i("Address: " + address);
//        }
        return new AddressProtocol(knownPeers);
    }

}
