package com.rootsoft.taters.models.protocols;

/**
 * This should be the first message that a node sends out to the network.
 * The message contains its version and block count.
 */
public class VersionMessage extends ProtocolMessage {

    //Constants
    public static final String TAG = VersionMessage.class.getSimpleName();
    public static final String TYPE = "VERSION";

    //Attributes
    private int versionCode;
    private int blockCount;

    //Constructors
    public VersionMessage(int versionCode, int blockCount) {
        super(TYPE);
        this.versionCode = versionCode;
        this.blockCount = blockCount;
    }

    //Properties

    public int getVersionCode() {
        return versionCode;
    }

    public int getBlockCount() {
        return blockCount;
    }

}
