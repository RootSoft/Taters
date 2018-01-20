package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

import java.io.Serializable;

/**
 * A Protocol Message is a message to be sent on the P2P network.
 */
public abstract class Protocol implements Serializable {

    //Constants
    public static final String TAG = Protocol.class.getSimpleName();

    //Attributes
    private ProtocolType protocolType;
    private boolean resolved;

    //Constructors
    public Protocol(ProtocolType type) {
        this.protocolType = type;
    }

    //Properties
    public ProtocolType getType() {
        return protocolType;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void markResolved() {
        this.resolved = true;
    }

}