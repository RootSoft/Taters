package com.rootsoft.taters.models.protocols;

import com.rootsoft.taters.models.protocols.handlers.ProtocolHandler;
import com.rootsoft.taters.models.protocols.handlers.VersionHandler;
import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;

public class ProtocolExecutor {

    //Constants

    //Attributes
    private ProtocolHandler chain;

    //Constructor

    public ProtocolExecutor() {
        buildChain();
    }

    private void buildChain() {
        chain = new VersionHandler(null);
    }

    public ProtocolMessage resolveProtocolMessage(ProtocolMessage message) {
        return chain.resolveProtocol(message);
    }

}