package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

import java.io.Serializable;

/**
 * A Protocol Message is a message to be sent on the P2P network.
 */
public abstract class ProtocolMessage implements Serializable {

    //Constants
    public static final String TAG = ProtocolMessage.class.getSimpleName();

    //Attributes
    private ProtocolType type;

    //Constructors
    public ProtocolMessage(ProtocolType type) {
        this.type = type;
    }

    //Properties
    public ProtocolType getType() {
        return type;
    }

}
