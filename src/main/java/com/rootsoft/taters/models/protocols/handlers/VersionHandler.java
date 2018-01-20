package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.VerackMessage;
import com.rootsoft.taters.models.protocols.messages.VersionMessage;

public class VersionHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = VersionHandler.class.getSimpleName();

    //Attributes
    private VersionMessage message;

    //Constructors

    public VersionHandler(Node node, ProtocolHandler next) {
        super(node, next);
    }

    @Override
    public ProtocolMessage resolveProtocolRequest(ProtocolMessage message) {
        if (message.getType().equals(ProtocolType.VERSION)) {
            this.message = (VersionMessage) message;
            handleRequest();
            return response();
        }

        return super.resolveProtocolRequest(message);
    }

    @Override
    public void resolveProtocolResponse(ProtocolMessage message) {
        if (message.getType().equals(ProtocolType.VERSION)) {
            this.message = (VersionMessage) message;
            handleResponse();
            return;
        }

        super.resolveProtocolResponse(message);
    }

    private void handleRequest() {
        System.out.println("Received version: " + message.getVersionCode() + ", blockcount: " + message.getBlockCount() + " in bootstrap node");
    }

    private void handleResponse() {
        System.out.println("Received version: " + message.getVersionCode() + ", blockcount: " + message.getBlockCount() + " in initial node");
        //TODO dispatch a VerackMessage to establish the connection
        node.sendProtocolMessage(new VerackMessage(200));
    }

    @Override
    protected ProtocolMessage response() {
        return new VersionMessage(2, 12);
    }

}
