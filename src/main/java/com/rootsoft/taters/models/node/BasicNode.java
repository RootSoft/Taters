package com.rootsoft.taters.models.node;

import com.rootsoft.taters.models.node.implementations.NodeEventCallback;
import com.rootsoft.taters.models.protocols.messages.Protocol;

/**
 * Basic nodes are full blockchain nodes and contains the complete blockchain and network routing functions,
 * but do not perform the storage of private keys or mining
 */
public class BasicNode extends Node {

    //Constants
    public static final String TAG = BasicNode.class.getSimpleName();

    public BasicNode(String name, int port) {
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
        return true;
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
