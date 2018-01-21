package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

/**
 * A response on the VersionProtocol, accepting the connection request.
 */
public class VerackProtocol extends Protocol {

    //Constants
    public static final String TAG = VerackProtocol.class.getSimpleName();

    //Attributes
    private String name;
    private boolean connected;

    //Constructors
    public VerackProtocol(boolean connected, String name) {
        super(ProtocolType.VERACK);
        this.connected = connected;
        this.name = name;
    }

    //Properties

    public boolean isConnected() {
        return connected;
    }

    public String getName() {
        return name;
    }

}
