package com.rootsoft.taters.models.node;

import com.rootsoft.taters.models.protocols.ProtocolExecutor;
import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import net.tomp2p.peers.PeerAddress;

/**
 * A bootstrap node is a pre-configured node on the network that is a starting point for other nodes.
 * It provides initial configuration information to newly joining nodes so that they may successfully join the overlay network.
 */
public class BootstrapNode extends Node {

    //Constants
    public static final String TAG = BootstrapNode.class.getSimpleName();

    //Attributes

    //Constructors
    public BootstrapNode() {
        super("BootstrapNode", new NodeEventCallback() {

            @Override
            public void onMessageSent(ProtocolMessage message) {

            }

            @Override
            public ProtocolMessage onMessageReceived(PeerAddress sender, ProtocolMessage message) {
                System.out.println("I'm bootstrapnode and I just got the message [" + message
                        + "] from " + sender.peerId());
                ProtocolExecutor executor = new ProtocolExecutor();
                return executor.resolveProtocolMessage(message);
            }

            @Override
            public void onResponseReceived(ProtocolMessage message) {
                ProtocolExecutor executor = new ProtocolExecutor();
                executor.resolveProtocolMessage(message);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                System.err.println("Error: " + errorCode + ", message: " + errorMessage);
            }

        });

    }

    public BootstrapNode(String name, NodeEventCallback callback) {
        super(name, callback);
    }

    //Methods

    //Properties

}
