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

    //The "nonce" in is a 32-bit (4-byte) field whose value is set
    //so that the hash of the block will contain a run of leading zeros.
    private int nonce;

    //Constructors
    public Block(int index, long timestamp, String data) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = "";
        this.nonce = 0;

        hash = calculateHash();
    }

    //Properties
    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
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

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    //Methods
    public String calculateHash() {
        return DigestUtils.sha256Hex(index + previousHash + timestamp + data + nonce);
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
