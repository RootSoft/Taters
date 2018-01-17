package com.rootsoft.taters.models;

import com.rootsoft.taters.models.block.Block;
import com.rootsoft.taters.models.consensus.Consensus;
import com.rootsoft.taters.repositories.block.BlockRepository;

public class Blockchain {

    //Constants
    public static final String TAG = Blockchain.class.getSimpleName();

    //Properties
    private BlockRepository repository;
    private Consensus consensus;

    //Constructors
    public Blockchain(BlockRepository repository, Consensus consensus) {
        this.repository = repository;
        this.consensus = consensus;
    }

    //Methods

    public Block getGenesisBlock() {
        return repository.getGenesisBlock();
    }

    public Block getLastBlock() {
        return repository.getLastBlock();
    }

    public void createBlock(Block block) {
        block.setPreviousHash(getLastBlock().getHash());
        consensus.execute(block);
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
