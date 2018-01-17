package com.rootsoft.taters.models.consensus;

import com.rootsoft.taters.models.block.Block;

/**
 * Everyone can create a new block.
 */
public class NoConsensus implements Consensus {

    //Constants
    public static final String TAG = NoConsensus.class.getSimpleName();

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void execute(Block block) {

    }

}
