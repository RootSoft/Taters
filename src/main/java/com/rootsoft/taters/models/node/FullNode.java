package com.rootsoft.taters.models.node;

/**
 * Full nodes are implementations of core clients performing the wallet, miner,
 * full blockchain storage and network routing functions
 */
public class FullNode extends Node {

    //Constants
    public static final String TAG = FullNode.class.getSimpleName();

    //Attributes

    //Constructors
    public FullNode(String name, NodeEventCallback callback) {
        super(name, callback);
    }

    public FullNode(String name, Node masterNode, NodeEventCallback callback) {
        super(name, masterNode, callback);
    }

}
