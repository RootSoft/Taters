package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.utils.Log;
import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import com.rootsoft.taters.models.protocols.messages.VerackProtocol;

public class VerackHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = VerackHandler.class.getSimpleName();

    //Attributes
    private VerackProtocol protocol;

    //Constructors

    public VerackHandler(Node node, ProtocolHandler next) {
        super(node, next);
    }

    @Override
    public Protocol resolveProtocolRequest(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.VERACK)) {
            this.protocol = (VerackProtocol) protocol;
            handleRequest();
            return response();
        }

        return super.resolveProtocolRequest(protocol);
    }

    @Override
    public void resolveProtocolResponse(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.VERACK)) {
            this.protocol = (VerackProtocol) protocol;
            handleResponse();
            return;
        }

        super.resolveProtocolResponse(protocol);
    }

    private void handleRequest() {
        Log.i("Accepting " + protocol.getStatusCode() + " connection request...");
    }

    private void handleResponse() {
        Log.i("Connection request was accepted: " + protocol.getStatusCode());
    }

    @Override
    protected Protocol response() {
        return new VerackProtocol(200);
    }

}
