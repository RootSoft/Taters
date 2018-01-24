package com.rootsoft.taters.models;

import com.rootsoft.taters.models.node.Node;

import java.util.List;

public interface ConnectionCallback {

    /**
     * A new node has connected to the network.
     */
    void onConnected(Node peer, Node bootstrap);

    /**
     * A node has disconnected from the network
     */
    void onDisconnect(Node peer);

    /**
     * A node was unable to connect or joing a network.
     */
    void onError(Node peer);

}
