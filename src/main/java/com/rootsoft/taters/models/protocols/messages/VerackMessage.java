package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

/**
 * A response on the VersionMessage, accepting the connection request.
 */
public class VerackMessage extends ProtocolMessage {

    //Constants
    public static final String TAG = VerackMessage.class.getSimpleName();

    //Attributes
    private int statusCode;

    //Constructors
    public VerackMessage(int statusCode) {
        super(ProtocolType.VERACK);
        this.statusCode = statusCode;
    }

    //Properties

    public int getStatusCode() {
        return statusCode;
    }
}
