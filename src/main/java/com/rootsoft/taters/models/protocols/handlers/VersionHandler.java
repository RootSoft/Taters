package com.rootsoft.taters.models.protocols.handlers;

import com.rootsoft.taters.models.protocols.messages.VersionProtocol;
import com.rootsoft.taters.utils.Log;
import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import com.rootsoft.taters.models.protocols.ProtocolType;
import com.rootsoft.taters.models.protocols.messages.VerackProtocol;

public class VersionHandler extends ProtocolHandler {

    //Constants
    public static final String TAG = VersionHandler.class.getSimpleName();

    //Attributes
    private VersionProtocol protocol;

    //Constructors

    public VersionHandler(Node node) {
        super(node);
    }

    public VersionHandler(Node node, ProtocolHandler next) {
        super(node, next);
    }

    @Override
    public Protocol resolveProtocolRequest(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.VERSION)) {
            this.protocol = (VersionProtocol) protocol;
            handleRequest();
            return response();
        }

        return super.resolveProtocolRequest(protocol);
    }

    @Override
    public void resolveProtocolResponse(Protocol protocol) {
        if (protocol.getType().equals(ProtocolType.VERSION)) {
            this.protocol = (VersionProtocol) protocol;
            handleResponse();
            return;
        }

        super.resolveProtocolResponse(protocol);
    }

    private void handleRequest() {
        Log.i("Received version: " + protocol.getVersionCode() + ", blockcount: " + protocol.getBlockCount() + " in bootstrap node");
    }

    private void handleResponse() {
        Log.i("Received version: " + protocol.getVersionCode() + ", blockcount: " + protocol.getBlockCount() + " in initial node");
        node.sendProtocol(new VerackProtocol(true, node.getName()));
    }

    @Override
    protected Protocol response() {
        return new VersionProtocol(2, 12);
    }

}
