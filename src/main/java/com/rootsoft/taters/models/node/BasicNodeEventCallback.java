package com.rootsoft.taters.models.node;

import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

import javax.xml.soap.Node;

public class BasicNodeEventCallback implements NodeEventCallback {

    //Constants
    public static final String TAG = BasicNodeEventCallback.class.getSimpleName();

    @Override
    public void onMessageSent(String key) {
        System.out.println("Message with key " + key + " was sent.");
    }

    @Override
    public Object onMessageReceived(PeerAddress sender, Object message) {
        System.err.println("I'm " + TAG + " and I just got the message [" + message
                + "] from " + sender.peerId());
        return null;
    }

    @Override
    public void onDataPut(com.rootsoft.taters.models.node.Node sender, Number160 key) {

    }

    @Override
    public void onDataReceived(com.rootsoft.taters.models.node.Node sender, Number160 key, Data data) {

    }

    @Override
    public void onError(int errorCode, String errorMessage) {
        System.out.println("Error: " + errorCode + ", Message: " + errorMessage);
    }

}
