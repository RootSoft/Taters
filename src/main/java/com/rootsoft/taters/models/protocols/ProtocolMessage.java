package com.rootsoft.taters.models.protocols;

import java.io.Serializable;

/**
 * A Protocol Message is a message
 */
public abstract class ProtocolMessage implements Serializable {

    //Constants
    public static final String TAG = ProtocolMessage.class.getSimpleName();

    //Attributes
    private String type;

    //Constructors
    public ProtocolMessage(String type) {
        this.type = type;
    }

    //Properties
    public String getType() {
        return type;
    }

}
