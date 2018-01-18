package com.rootsoft.taters.utils;

import com.rootsoft.taters.models.commands.ProtocolCommand;
import com.rootsoft.taters.models.node.FullNode;
import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.node.NodeEventCallback;
import com.rootsoft.taters.models.protocols.ProtocolFactory;
import com.rootsoft.taters.models.protocols.ProtocolMessage;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

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
                ProtocolFactory.createProtocol(message).execute();
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                System.err.println("Error: " + errorCode + ", message: " + errorMessage);
            }

        });
    }
}
