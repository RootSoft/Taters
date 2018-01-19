package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.VersionMessage;

public class VersionHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = VersionHandler.class.getSimpleName();

    //Attributes
    private VersionMessage message;

    //Constructors

    public VersionHandler(ProtocolHandler next) {
        super(next);
    }

    @Override
    public ProtocolMessage resolveProtocol(ProtocolMessage message) {
        if (message.getType().equals(ProtocolType.VERSION)) {
            this.message = (VersionMessage) message;
            handleRequest();
            return response();
        }

        return super.resolveProtocol(message);
    }

    @Override
    protected void handleRequest() {
        System.out.println("Received version: " + message.getVersionCode() + ", blockcount: " + message.getBlockCount());
    }

    @Override
    protected ProtocolMessage response() {
        return new VersionMessage(2, 12);
    }

}
