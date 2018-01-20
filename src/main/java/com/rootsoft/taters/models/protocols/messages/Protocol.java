package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;
import net.tomp2p.peers.PeerAddress;

import java.io.Serializable;

/**
 * A Protocol Message is a message to be sent on the P2P network.
 */
public abstract class Protocol implements Serializable {

    //Constants
    public static final String TAG = Protocol.class.getSimpleName();

    //Attributes
    private ProtocolType protocolType;
    private boolean resolved;
    private PeerAddress sender;

    //Constructors
    public Protocol(ProtocolType type) {
        this.protocolType = type;
    }

    //Properties
    public ProtocolType getType() {
        return protocolType;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void markResolved() {
        this.resolved = true;
    }

    public PeerAddress getSender() {
        return sender;
    }

    public void setSender(PeerAddress sender) {
        this.sender = sender;
    }
}
