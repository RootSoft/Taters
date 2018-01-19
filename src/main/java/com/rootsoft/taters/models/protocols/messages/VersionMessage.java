package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

/**
 * This should be the first message that a node sends out to the network.
 * The message contains its version and block count.
 */
public class VersionMessage extends ProtocolMessage {

    //Constants
    public static final String TAG = VersionMessage.class.getSimpleName();

    //Attributes
    private int versionCode;
    private int blockCount;

    //Constructors
    public VersionMessage(int versionCode, int blockCount) {
        super(ProtocolType.VERSION);
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
