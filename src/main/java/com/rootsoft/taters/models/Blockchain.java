package com.rootsoft.taters.models;

import com.rootsoft.taters.models.block.Block;
import com.rootsoft.taters.models.block.GenesisBlock;
import com.rootsoft.taters.repositories.BlockRepository;

public class Blockchain {

    //Constants
    public static final String TAG = Blockchain.class.getSimpleName();

    //Properties
    private BlockRepository repository;

    //Constructors
    public Blockchain(BlockRepository repository) {
        this.repository = repository;
    }

    //Methods

    public Block getGenesisBlock() {
        return repository.getGenesisBlock();
    }

    public Block getLastBlock() {
        return repository.getLastBlock();
    }

    public void addBlock(Block block) {
        block.setPreviousHash(getLastBlock().getHash());
        block.setHash(block.calculateHash());
        repository.addBlock(block);
    }

    @Override
    public String toString() {
        return "Blockchain{" + repository + '}';
    }

}
