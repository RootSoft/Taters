package com.rootsoft.taters.models.node;

public enum NodeType {
    /**
     * Basic nodes are full blockchain nodes and contains the complete blockchain and network routing functions,
     * but do not perform the storage of private keys or mining
     */
    BASIC,

    /**
     * A bootstrap node is a pre-configured node on the network that is a starting point for other nodes.
     * It provides initial configuration information to newly joining nodes so that they may successfully join the overlay network.
     */
    BOOTSTRAP,

    /**
     * A mining node only performs mining, full blockchain storage and network routing functionality.
     */
    SOLO_MINER,

    /**
     * A node that does not verify everything, but instead relies on either connecting to a trusted node,
     * or puts its faith in high difficulty as a proxy for proof of validity.
     * SPV (Single Payment Verification) nodes perform only wallet or network routing functionality.
     */
    SPV,

    /**
     * Full nodes perform wallet, mining, full blockchain storage and network routing functionality.
     */
    FULL
}
