package com.rootsoft.taters.models.consensus;

import com.rootsoft.taters.models.block.Block;

/**
 * A consensus algorithm does two things:
 * - it ensures that the next block in a blockchain is the one and only version of the truth,
 * - it keeps powerful adversaries from derailing the system and successfully forking the chain.
 */
public interface Consensus {

    /**
     * Validates that the next block in a blockchain is the one and only version of the truth.
     */
    boolean validate();

    /**
     * Executes the algoritm
     */
    void execute(Block block);

}
