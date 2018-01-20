package com.rootsoft.taters.models.node.implementations;

import com.rootsoft.taters.models.protocols.messages.Protocol;
import net.tomp2p.peers.PeerAddress;

public interface NodeEventCallback {

    /**
     * Callback when a message was sent successfully to the peers.
     * @param message The protocol message
     */
    void onMessageSent(Protocol message);

    /**
     * Callback when a message was received from another peer.
     * We can return an object as a reply to the sender
     */
    Protocol onProtocolRequestReceived(Protocol message);

    /**
     * Callback when a response was sent from a peer for your given request.
     */
    void onProtocolResponseReceived(Protocol message);

    /**
     * Callback when an error occured on the network.
     */
    void onError(int errorCode, String errorMessage);

}
