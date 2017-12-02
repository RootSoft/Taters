package com.rootsoft.taters.models;

import com.rootsoft.taters.models.block.Block;
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

    /**
     * Checks if the chain is valid and has not been tampered with.
     * @return true if the chain is valid
     */
    public boolean isChainValid() {

        for (int i=1; i<=repository.getSize()-1; i++) {
            Block currentBlock = repository.getBlock(i);
            Block previousBlock = repository.getBlock(i-1);

            System.out.println("Current Block: " + currentBlock);
            System.out.println("Previous Block: " + previousBlock);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Blockchain{" + repository + '}';
    }

}
