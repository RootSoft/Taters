package com.rootsoft.taters.models.node;

import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import net.tomp2p.peers.PeerAddress;

public interface NodeEventCallback {

    /**
     * Callback when a message was sent successfully to the peers.
     * @param message The protocol message
     */
    void onMessageSent(ProtocolMessage message);

    /**
     * Callback when a message was received from another peer.
     * We can return an object as a reply to the sender
     */
    ProtocolMessage onMessageReceived(PeerAddress sender, ProtocolMessage message);

    /**
     * Callback when a response was sent from a peer for your given request.
     */
    void onResponseReceived(ProtocolMessage message);

    /**
     * Callback when an error occured on the network.
     */
    void onError(int errorCode, String errorMessage);

}
