package com.rootsoft.taters.models.node;

import com.rootsoft.taters.utils.Log;
import com.rootsoft.taters.models.node.implementations.NodeEventCallback;
import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import net.tomp2p.peers.PeerAddress;

/**
 * A bootstrap node is a pre-configured node on the network that is a starting point for other nodes.
 * It provides initial configuration information to newly joining nodes so that they may successfully join the overlay network.
 */
public class BootstrapNode extends Node {

    //Constants
    public static final String TAG = BootstrapNode.class.getSimpleName();

    public BootstrapNode(String name) {
        super(name);
        setNodeEventCallback(callback);
    }

    public BootstrapNode(String name, NodeEventCallback callback) {
        super(name, callback);
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
        public void onMessageSent(ProtocolMessage message) {

        }

        @Override
        public ProtocolMessage onProtocolRequestReceived(PeerAddress sender, ProtocolMessage message) {
            Log.i("I'm bootstrapnode and I just got the message [" + message
                    + "] from " + sender.peerId());

            return executor.resolveProtocolRequest(message);
        }

        @Override
        public void onProtocolResponseReceived(PeerAddress sender, ProtocolMessage message) {
            executor.resolveProtocolResponse(message);
        }

        @Override
        public void onError(int errorCode, String errorMessage) {

        }

    };

}
