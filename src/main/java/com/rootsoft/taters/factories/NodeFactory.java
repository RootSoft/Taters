package com.rootsoft.taters.factories;


import com.rootsoft.taters.models.node.*;

public class NodeFactory {

    //Constants
    public static final String TAG = NodeFactory.class.getSimpleName();

    //Attributes

    public static Node createNode(String name, NodeType type) {

        switch (type) {
            case BASIC:
                return new BasicNode(name, 4001);
            case BOOTSTRAP:
                return new BootstrapNode(name, 4002);
            case SOLO_MINER:
                return new BasicNode(name, 4003);
            case SPV:
                return new SPVNode(name, 4004);
            case FULL:
                return new BasicNode(name, 4005);
            default:
                return new BasicNode(name, 4000);
        }
    }

}
