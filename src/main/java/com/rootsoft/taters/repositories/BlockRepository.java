package com.rootsoft.taters.repositories;

import com.rootsoft.taters.models.block.Block;

/**
 * Implementation of the repository pattern to support other storage options later on.
 */
public interface BlockRepository {

    /**
     * Adds a new block to the chain.
     * @param block the block to be added.
     */
    void addBlock(Block block);

    /**
     * Get the last added block from the chain.
     */
    Block getLastBlock();

    /**
     * Gets the genesis block from the chain.
     */
    Block getGenesisBlock();

    /**
     * Gets a block at a specific position in the chain.
     */
    Block getBlock(int position);

    /**
     * Gets the length of the blockchain.
     * @return the length of the blockchain
     */
    int getSize();

}
