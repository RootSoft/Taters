package com.rootsoft.taters.models.node;

import com.rootsoft.taters.utils.Log;
import com.rootsoft.taters.models.node.implementations.NodeEventCallback;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import net.tomp2p.peers.PeerAddress;

/**
 * A bootstrap node is a pre-configured node on the network that is a starting point for other nodes.
 * It provides initial configuration information to newly joining nodes so that they may successfully join the overlay network.
 */
public class BootstrapNode extends Node {

    //Constants
    public static final String TAG = BootstrapNode.class.getSimpleName();

    public BootstrapNode(String name, int port) {
        super(name, port);
        setNodeEventCallback(callback);
    }

    @Override
    public boolean canMine() {
        return false;
    }

    @Override
    public boolean canRoute() {
        return true;
    }

    @Override
    public boolean hasCompleteBlockchain() {
        return false;
    }

    @Override
    public boolean hasWallet() {
        return false;
    }

    NodeEventCallback callback = new NodeEventCallback() {

        @Override
        public void onMessageSent(Protocol message) {

        }

        @Override
        public Protocol onProtocolRequestReceived(Protocol message) {
            return executor.resolveProtocolRequest(message);
        }

        @Override
        public void onProtocolResponseReceived(Protocol message) {
            executor.resolveProtocolResponse(message);
        }

        @Override
        public void onError(int errorCode, String errorMessage) {

        }

    };

}
