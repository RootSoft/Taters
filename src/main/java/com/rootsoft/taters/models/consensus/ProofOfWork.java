package com.rootsoft.taters.models.consensus;

import com.rootsoft.taters.models.block.Block;

import java.util.Arrays;

/**
 *
 */
public class ProofOfWork implements Consensus {

    //Constants
    public static final String TAG = ProofOfWork.class.getSimpleName();

    //Attributes
    private int difficulty;

    //Constructors
    public ProofOfWork(int difficulty) {
        this.difficulty = difficulty;
    }

    //Properties
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * The goal is to find a hash below a target number which is calculated based on the difficulty.
     * Proof of work in Bitcoin's mining takes an input consists of Merkle Root, timestamp, previous block hash
     * plus a nonce which is completely random number.
     *
     * TODO Should be run on a thread
     */
    @Override
    public void execute(Block block) {
        String[] solve = new String[difficulty + 1];
        Arrays.fill(solve, "");

        while (!block.getHash().substring(0, difficulty).equals(String.join("0", Arrays.asList(solve)))) {
            block.setNonce(block.getNonce() + 1);
            block.setHash(block.calculateHash());
        }
    }

}
