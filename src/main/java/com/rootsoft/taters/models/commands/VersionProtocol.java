package com.rootsoft.taters.models.commands;

import com.rootsoft.taters.models.protocols.ProtocolMessage;
import com.rootsoft.taters.models.protocols.VersionMessage;

public class VersionProtocol implements ProtocolCommand {

    //Constants
    public static final String TAG = VersionProtocol.class.getSimpleName();

    //Attributes
    private VersionMessage message;

    //Constructors
    public VersionProtocol(VersionMessage message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println("Received version: " + message.getVersionCode() + ", blockcount: " + message.getBlockCount());
    }

    @Override
    public ProtocolMessage response() {
        System.out.println("Sending data back to requester");
        return new VersionMessage(2, 12);
    }

}
