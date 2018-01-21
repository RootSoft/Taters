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
import net.tomp2p.peers.PeerAddress;

import java.util.ArrayList;
import java.util.List;

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

    private void handleResponse() {
        for (NodeAddress address : protocol.getKnownPeers()) {
            Log.i("Address: " + address.toString());
        }
    }

    @Override
    protected Protocol response() {
        return null;
    }

}
