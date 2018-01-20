package com.rootsoft.taters.models.protocols;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.handlers.GetAddressHandler;
import com.rootsoft.taters.models.protocols.handlers.ProtocolHandler;
import com.rootsoft.taters.models.protocols.handlers.VerackHandler;
import com.rootsoft.taters.models.protocols.handlers.VersionHandler;
import com.rootsoft.taters.models.protocols.messages.Protocol;

public class ProtocolExecutor {

    //Constants

    //Attributes
    private ProtocolHandler chain;

    //Constructor

    public ProtocolExecutor(Node node) {
        chain = buildChain(node);
    }

    private ProtocolHandler buildChain(Node node) {
        ProtocolHandler versionHandler = new VersionHandler(node);
        ProtocolHandler verackHandler = new VerackHandler(node);
        ProtocolHandler getAddressHandler = new GetAddressHandler(node);

        versionHandler.setNextHandler(verackHandler);
        verackHandler.setNextHandler(getAddressHandler);
        return versionHandler;
    }

    public Protocol resolveProtocolRequest(Protocol message) {
        return chain.resolveProtocolRequest(message);
    }

    public void resolveProtocolResponse(Protocol message) {
        chain.resolveProtocolResponse(message);
    }

}
