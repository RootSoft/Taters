package com.rootsoft.taters.models.protocols;

import com.rootsoft.taters.models.node.Node;
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
        buildChain(node);
    }

    private void buildChain(Node node) {
        chain = new VersionHandler(node, new VerackHandler(node, null));
    }

    public Protocol resolveProtocolRequest(Protocol message) {
        return chain.resolveProtocolRequest(message);
    }

    public void resolveProtocolResponse(Protocol message) {
        chain.resolveProtocolResponse(message);
    }

}
