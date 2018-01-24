package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

/**
 * Send a ping message on the network and see who responds.
 */
public class PingProtocol extends Protocol {

    //Constants
    public static final String TAG = PingProtocol.class.getSimpleName();

    //Attributes
    private String message;

    //Constructors
    public PingProtocol(String message) {
        super(ProtocolType.PING);
        this.message = message;
    }

    //Properties

    public String getMessage() {
        return message;
    }
}
