package com.rootsoft.taters.models;

import com.rootsoft.taters.models.block.Block;
import com.rootsoft.taters.models.block.GenesisBlock;

import java.util.LinkedList;

public class Blockchain {

    //Constants
    public static final String TAG = Blockchain.class.getSimpleName();

    //Properties
    private LinkedList<Block> chain;

    //Constructors
    public Blockchain() {
        chain = new LinkedList<>();
        chain.add(new GenesisBlock());
    }

    //Methods

    public Block getGenesisBlock() {
        return chain.peekLast();
    }

    public Block getLastBlock() {
        return chain.peekFirst();
    }

    public void addBlock(Block block) {
        block.setPreviousHash(getLastBlock().getHash());
        block.setHash(block.calculateHash());
        chain.push(block);
    }

    @Override
    public String toString() {
        return "Blockchain{" + chain + '}';
    }

}
