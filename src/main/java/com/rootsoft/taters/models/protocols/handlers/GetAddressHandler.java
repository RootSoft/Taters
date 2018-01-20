package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.GetAddressProtocol;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import com.rootsoft.taters.models.protocols.messages.VerackProtocol;
import com.rootsoft.taters.utils.Log;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.PeerAddress;

import java.util.ArrayList;
import java.util.List;

public class GetAddressHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = GetAddressHandler.class.getSimpleName();

    //Attributes
    private GetAddressProtocol protocol;
    private List<PeerAddress> knownPeers;

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

    @Override
    public void resolveProtocolResponse(Protocol protocol) {

    }

    private void handleRequest() {
        knownPeers.addAll(node.getKnownPeers());
    }

    private void handleResponse() {
    }

    @Override
    protected Protocol response() {
        //TODO return a AddressProtocol with a list of IP addresses.
        Log.i("Returning AddressProtocol with list of ip addresses");
        return null;
    }

}
