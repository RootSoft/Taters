package com.rootsoft.taters.models.node;

import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

public interface NodeEventCallback {

    /**
     * Callback when a message was sent successfully to the peers.
     * @param key The key of the message
     */
    void onMessageSent(String key);

    /**
     * Callback when a message was received from another peer.
     * We can return an object as a reply to the sender
     */
    Object onMessageReceived(PeerAddress sender, Object message);

    /**
     * Callback when data was successfully put on the network.
     * @param key
     */
    void onDataPut(Number160 key);

    /**
     * Callback when data was fetched from the network.
     * @param sender
     * @param key
     * @param data
     */
    void onDataReceived(Number160 key, Data data);

    /**
     * Callback when an error occured on the network.
     */
    void onError(int errorCode, String errorMessage);
}
