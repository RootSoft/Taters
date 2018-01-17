package com.rootsoft.taters.repositories.block;

import com.rootsoft.taters.models.block.Block;
import com.rootsoft.taters.models.block.GenesisBlock;

import java.util.ArrayList;

public class BlockListRepository implements BlockRepository {

    //Constants
    public static final String TAG = BlockListRepository.class.getSimpleName();

    //Properties
    private ArrayList<Block> chain;

    //Constructors
    public BlockListRepository() {
        chain = new ArrayList<>();
        chain.add(new GenesisBlock());
    }

    @Override
    public void addBlock(Block block) {
        chain.add(block);
    }

    @Override
    public Block getLastBlock() {
        return chain.get(chain.size()-1);
    }

    @Override
    public Block getGenesisBlock() {
        return chain.get(0);
    }

    @Override
    public Block getBlock(int position) {
        return chain.get(position);
    }

    @Override
    public int getSize() {
        return chain.size();
    }

    @Override
    public String toString() {
        return "{" + chain + '}';
    }

}
