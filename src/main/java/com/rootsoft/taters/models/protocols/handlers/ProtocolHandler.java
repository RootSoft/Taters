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
    public ProtocolMessage resolveProtocolRequest(ProtocolMessage message) {
        if (next != null) {
            return next.resolveProtocolRequest(message);
        }
        return null;
    }

    public void resolveProtocolResponse(ProtocolMessage message) {
        if (next != null) {
            next.resolveProtocolResponse(message);
        }
    }

    //Properties

    protected abstract ProtocolMessage response();

}
