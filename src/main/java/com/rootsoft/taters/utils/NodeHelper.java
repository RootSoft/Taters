package com.rootsoft.taters.utils;

import com.rootsoft.taters.models.node.FullNode;
import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.node.NodeEventCallback;
import com.rootsoft.taters.models.protocols.ProtocolExecutor;
import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import net.tomp2p.peers.PeerAddress;

public class NodeHelper {

    public static Node createFullNode(String name) {
        return new FullNode(name, new NodeEventCallback() {

            @Override
            public void onMessageSent(ProtocolMessage message) {
            }

            @Override
            public ProtocolMessage onMessageReceived(PeerAddress sender, ProtocolMessage message) {
                System.out.println("I'm " + name + " and I just got the message [" + message
                        + "] from " + sender.peerId());
                return null;
            }

            @Override
            public void onResponseReceived(ProtocolMessage message) {
                ProtocolExecutor executor = new ProtocolExecutor();
                executor.resolveProtocolResponse(message);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                System.err.println("Error: " + errorCode + ", message: " + errorMessage);
            }

        });
    }
}
