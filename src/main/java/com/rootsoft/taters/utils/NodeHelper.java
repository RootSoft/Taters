package com.rootsoft.taters.utils;

import com.rootsoft.taters.models.node.Node;
import com.rootsoft.taters.models.node.NodeEventCallback;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

public class NodeHelper {

    public static Node createNode(String name) {
        return new Node(name, new NodeEventCallback() {

            @Override
            public void onMessageSent(String key) {
                System.out.println("Message with key " + key + " was sent.");
            }

            @Override
            public Object onMessageReceived(PeerAddress sender, Object message) {
                System.out.println("I'm " + name + " and I just got the message [" + message
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
                System.err.println("Error: " + errorCode + ", Message: " + errorMessage);
            }

        });
    }
}
