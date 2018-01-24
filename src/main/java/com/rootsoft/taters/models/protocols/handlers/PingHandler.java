package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.GetAddressProtocol;
import com.rootsoft.taters.models.protocols.messages.PingProtocol;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import com.rootsoft.taters.models.protocols.messages.VerackProtocol;
import com.rootsoft.taters.utils.Log;

public class PingHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = PingHandler.class.getSimpleName();

    //Attributes
    private PingProtocol protocol;

    //Constructors

    public PingHandler(Node node) {
        super(node);
    }

    public PingHandler(Node node, ProtocolHandler next) {
        super(node, next);
    }

    @Override
    public Protocol resolveProtocolRequest(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.PING)) {
            this.protocol = (PingProtocol) protocol;
            handleRequest();
            return response();
        }

        return super.resolveProtocolRequest(protocol);
    }

    @Override
    public void resolveProtocolResponse(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.PING)) {
            this.protocol = (PingProtocol) protocol;
            handleResponse();
            return;
        }
        super.resolveProtocolResponse(protocol);
    }

    private void handleRequest() {
        Log.i(node.getName() + " received ping: " + protocol.getMessage());
    }

    private void handleResponse() {
        Log.i(node.getName() + " received ping: " + protocol.getMessage());
    }

    @Override
    protected Protocol response() {
        return new PingProtocol("Pong");
    }

}
