package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.messages.Protocol;

public abstract class ProtocolHandler {

    //Constants
    public static final String TAG = ProtocolHandler.class.getSimpleName();

    //Attributes
    protected Node node;
    private ProtocolHandler next;

    //Constructors

    public ProtocolHandler(Node node) {
        this.node = node;
    }

    public ProtocolHandler(Node node, ProtocolHandler next) {
        this.node = node;
        this.next = next;
    }

    //Methods

    public ProtocolHandler getNextHandler() {
        return next;
    }

    public void setNextHandler(ProtocolHandler next) {
        this.next = next;
    }

    public Protocol resolveProtocolRequest(Protocol protocol) {
        if (next != null) {
            return next.resolveProtocolRequest(protocol);
        }
        return null;
    }

    public void resolveProtocolResponse(Protocol protocol) {
        if (next != null) {
            next.resolveProtocolResponse(protocol);
        }
    }

    //Properties

    protected abstract Protocol response();

}
