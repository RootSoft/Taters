package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import com.rootsoft.taters.models.protocols.messages.VerackMessage;
import com.rootsoft.taters.models.protocols.messages.VersionMessage;

public class VerackHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = VerackHandler.class.getSimpleName();

    //Attributes
    private VerackMessage message;

    //Constructors

    public VerackHandler(Node node, ProtocolHandler next) {
        super(node, next);
    }

    @Override
    public ProtocolMessage resolveProtocolRequest(ProtocolMessage message) {
        if (message.getType().equals(ProtocolType.VERACK)) {
            this.message = (VerackMessage) message;
            handleRequest();
            return response();
        }

        return super.resolveProtocolRequest(message);
    }

    @Override
    public void resolveProtocolResponse(ProtocolMessage message) {
        if (message.getType().equals(ProtocolType.VERACK)) {
            this.message = (VerackMessage) message;
            handleResponse();
            return;
        }

        super.resolveProtocolResponse(message);
    }

    private void handleRequest() {
        System.out.println("Accepting " + message.getStatusCode() + " connection request...");
    }

    private void handleResponse() {
        System.out.println("Connection request was accepted: " + message.getStatusCode());
    }

    @Override
    protected ProtocolMessage response() {
        return new VerackMessage(200);
    }

}
