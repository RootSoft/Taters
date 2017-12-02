package com.rootsoft.taters.repositories;

import com.rootsoft.taters.models.block.Block;
import com.rootsoft.taters.models.block.GenesisBlock;

import java.util.LinkedList;

public class BlockListRepository implements BlockRepository {

    //Constants
    public static final String TAG = BlockListRepository.class.getSimpleName();

    //Properties
    private LinkedList<Block> chain;

    //Constructors
    public BlockListRepository() {
        chain = new LinkedList<>();
        chain.add(new GenesisBlock());
    }

    @Override
    public void addBlock(Block block) {
        chain.push(block);
    }

    @Override
    public Block getLastBlock() {
        return chain.peekFirst();
    }

    @Override
    public Block getGenesisBlock() {
        return chain.peekLast();
    }

    @Override
    public String toString() {
        return "{" + chain + '}';
    }

}
