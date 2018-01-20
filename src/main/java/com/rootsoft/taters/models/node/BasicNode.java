package com.rootsoft.taters.models.node;

import com.rootsoft.taters.models.node.implementations.NodeEventCallback;

/**
 * Basic nodes are full blockchain nodes and contains the complete blockchain and network routing functions,
 * but do not perform the storage of private keys or mining
 */
public class BasicNode extends Node {

    //Constants
    public static final String TAG = BasicNode.class.getSimpleName();

    public BasicNode(String name) {
        super(name);
    }

    public BasicNode(String name, NodeEventCallback callback) {
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
        return true;
    }

    @Override
    public boolean hasWallet() {
        return false;
    }


}
