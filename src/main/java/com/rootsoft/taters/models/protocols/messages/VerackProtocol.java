package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

/**
 * A response on the VersionProtocol, accepting the connection request.
 */
public class VerackProtocol extends Protocol {

    //Constants
    public static final String TAG = VerackProtocol.class.getSimpleName();

    //Attributes
    private int statusCode;

    //Constructors
    public VerackProtocol(int statusCode) {
        super(ProtocolType.VERACK);
        this.statusCode = statusCode;
    }

    //Properties

    public int getStatusCode() {
        return statusCode;
    }
}
