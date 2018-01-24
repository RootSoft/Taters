package com.rootsoft.taters.models;


import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.factories.NodeFactory;
import com.rootsoft.taters.models.node.NodeType;
import com.rootsoft.taters.models.protocols.messages.VersionProtocol;
import net.tomp2p.futures.BaseFutureAdapter;
import net.tomp2p.futures.FutureBootstrap;

import java.util.ArrayList;
import java.util.List;

/**
 * The PeerNetwork is a P2P network where nodes exchange transactions and blocks.
 */
public class PeerNetwork {

    //Constants
    public static final String TAG = PeerNetwork.class.getSimpleName();

    //Attributes
    private Node bootstrapNode;
    private int status;
    private List<Node> nodes;

    private ConnectionCallback callback;

    //Constructors

    public PeerNetwork(ConnectionCallback callback) {
        this.callback = callback;
        nodes = new ArrayList<>();
        bootstrapNode = NodeFactory.createNode("BootstrapNode", NodeType.BOOTSTRAP);
    }


    //Methods

    /**
     * Let the node join the network.
     * We join the network by bootstrapping to our known peers.
     */
    public boolean join(final Node node) {
        if (node.getPeer() == null || bootstrapNode.getPeer() == null)
            return false;

        FutureBootstrap bootstrap = node.getPeer().peer().bootstrap().peerAddress(bootstrapNode.getPeer().peerAddress()).start();
        bootstrap.addListener(new BaseFutureAdapter<FutureBootstrap>() {

            @Override
            public void operationComplete(FutureBootstrap future) throws Exception {
                if (!future.isCompleted()) {
                    callback.onError(node);
                }

                node.setConnected(true);
                node.setPeerNetwork(PeerNetwork.this);
                nodes.add(node);

                callback.onConnected(node, bootstrapNode);
            }
        });

        return true;
    }

    //Properties

    public int getStatus() {
        return status;
    }

    public List<Node> getActiveConnections() {
        return nodes;
    }

}
