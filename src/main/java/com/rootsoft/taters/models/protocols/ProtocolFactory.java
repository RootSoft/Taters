package com.rootsoft.taters.models.protocols;

import com.rootsoft.taters.models.commands.DefaultProtocol;
import com.rootsoft.taters.models.commands.ProtocolCommand;
import com.rootsoft.taters.models.commands.VersionProtocol;

public class ProtocolFactory {

    //Constants
    public static final String TAG = ProtocolFactory.class.getSimpleName();

    /**
     * Create a new Protocol based on the incoming message.
     * @param message the message received from the client
     * @return a protocol command to be executed
     */
    public static ProtocolCommand createProtocol(ProtocolMessage message) {
        switch (message.getType()) {
            case VersionMessage.TYPE:
                return new VersionProtocol((VersionMessage) message);

            default:
                return new DefaultProtocol();

        }
    }

}
