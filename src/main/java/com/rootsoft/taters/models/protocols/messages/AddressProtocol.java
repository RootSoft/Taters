package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.node.NodeAddress;
import com.rootsoft.taters.models.protocols.ProtocolType;
import net.tomp2p.peers.PeerAddress;

import java.util.List;

/**
 * This provides information about nodes on the network. It contains the number of addresses.
 */
public class AddressProtocol extends Protocol {

    //Constants
    public static final String TAG = AddressProtocol.class.getSimpleName();

    //Attributes
    private List<NodeAddress> knownPeers;

    //Constructors
    public AddressProtocol(List<NodeAddress> knownPeers) {
        super(ProtocolType.ADDR);
        this.knownPeers = knownPeers;

    }

    //Properties

    public List<NodeAddress> getKnownPeers() {
        return knownPeers;
    }

}
