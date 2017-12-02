package com.rootsoft.taters.repositories;

import com.rootsoft.taters.models.block.Block;

/**
 * Implementation of the repository pattern to support other storage options later on.
 */
public interface BlockRepository {

    void addBlock(Block block);

    Block getLastBlock();

    Block getGenesisBlock();

}
