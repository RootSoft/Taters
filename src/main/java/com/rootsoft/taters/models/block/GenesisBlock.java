package com.rootsoft.taters.models.block;

import org.joda.time.DateTime;

/**
 * The genesis block is the first block on the blockchain.
 * This block always needs to be created manually.
 */
public class GenesisBlock extends Block {

    public GenesisBlock() {
        super(0, new DateTime().getMillis(), "Genesis Block");
    }
}
