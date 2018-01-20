package com.rootsoft.taters.factories;


import com.rootsoft.taters.models.node.*;

public class NodeFactory {

    //Constants
    public static final String TAG = NodeFactory.class.getSimpleName();

    //Attributes

    public static Node createNode(String name, NodeType type) {

        switch (type) {
            case BASIC:
                return new BasicNode(name);
            case BOOTSTRAP:
                return new BootstrapNode(name);
            case SOLO_MINER:
                return new BasicNode(name);
            case SPV:
                return new SPVNode(name);
            case FULL:
                return new BasicNode(name);
            default:
                return new BasicNode(name);
        }
    }

}
