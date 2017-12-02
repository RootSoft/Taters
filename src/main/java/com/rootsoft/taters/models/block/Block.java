package com.rootsoft.taters.models.block;

import org.apache.commons.codec.digest.DigestUtils;

public class Block {

    //Constants
    public static final String TAG = Block.class.getSimpleName();

    //Attributes
    private int index;
    private long timestamp;
    private String data;
    private String hash, previousHash;

    //Constructors
    public Block(int index, long timestamp, String data) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = "";

        hash = calculateHash();
    }

    //Properties
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    //Methods
    public String calculateHash() {
        return DigestUtils.sha256Hex(index + previousHash + timestamp + data);
    }

    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", timestamp=" + timestamp +
                ", data='" + data + '\'' +
                ", hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                '}';
    }
}
