package com.rootsoft.taters.models.node;

/**
 * SPV nodes or light-weight clients perform only wallet and network routing functionality.
 */
public class SPVNode extends Node {

    //Constants
    public static final String TAG = SPVNode.class.getSimpleName();

    //Attributes

    //Constructors
    public SPVNode(String name, NodeEventCallback callback) {
        super(name, callback);
    }

    public SPVNode(String name, Node masterNode, NodeEventCallback callback) {
        super(name, masterNode, callback);
    }

}
