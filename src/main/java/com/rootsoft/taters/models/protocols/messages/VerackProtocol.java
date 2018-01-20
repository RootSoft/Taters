package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

/**
 * A response on the VersionProtocol, accepting the connection request.
 */
public class VerackProtocol extends Protocol {

    //Constants
    public static final String TAG = VerackProtocol.class.getSimpleName();

    //Attributes
    private boolean connected;

    //Constructors
    public VerackProtocol(boolean connected) {
        super(ProtocolType.VERACK);
        this.connected = connected;
    }

    //Properties

    public boolean isConnected() {
        return connected;
    }

}
