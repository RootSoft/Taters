package com.rootsoft.taters.models.node;

import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

/**
 * A bootstrap node is a pre-configured node on the network that is a starting point for other nodes.
 * It provides initial configuration information to newly joining nodes so that they may successfully join the overlay network.
 */
public class BootstrapNode extends Node{

    //Constants
    public static final String TAG = BootstrapNode.class.getSimpleName();

    //Attributes


    //Constructors
    public BootstrapNode() {
        super("BootstrapNode", new NodeEventCallback() {

            @Override
            public void onMessageSent(String key) {

            }

            @Override
            public Object onMessageReceived(PeerAddress sender, Object message) {
                System.err.println("I'm bootstrapnode and I just got the message [" + message
                        + "] from " + sender.peerId());
                return null;
            }

            @Override
            public void onDataPut(Number160 key) {

            }

            @Override
            public void onDataReceived(Number160 key, Data data) {

            }

            @Override
            public void onError(int errorCode, String errorMessage) {

            }

        });
    }

    public BootstrapNode(String name, NodeEventCallback callback) {
        super(name, callback);
    }

    //Methods

    //Properties

}
