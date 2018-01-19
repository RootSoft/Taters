package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;

public abstract class ProtocolHandler {

    //Constants
    public static final String TAG = ProtocolHandler.class.getSimpleName();

    //Attributes
    private ProtocolHandler next;

    //Constructors
    public ProtocolHandler(ProtocolHandler next) {
        this.next = next;
    }

    //Methods
    public ProtocolMessage resolveProtocol(ProtocolMessage message) {
        if (next != null) {
            return next.resolveProtocol(message);
        }
        return null;
    }

    protected abstract void handleRequest();

    protected abstract ProtocolMessage response();

}
