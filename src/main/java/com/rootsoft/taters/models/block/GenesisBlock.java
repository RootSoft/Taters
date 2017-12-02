package com.rootsoft.taters.models.block;

import org.joda.time.DateTime;

public class GenesisBlock extends Block {

    public GenesisBlock() {
        super(0, new DateTime().getMillis(), "Genesis Block");
    }
}
