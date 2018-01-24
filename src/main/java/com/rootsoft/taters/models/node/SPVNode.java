package com.rootsoft.taters.models.node;

import com.rootsoft.taters.utils.Log;
import com.rootsoft.taters.models.node.implementations.NodeEventCallback;
import com.rootsoft.taters.models.protocols.messages.Protocol;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;

/**
 * SPV (Single Payment Verification) nodes perform only wallet or network routing functionality.
 *
 * A node that does not verify everything, but instead relies on either connecting to a trusted node,
 * or puts its faith in high difficulty as a proxy for proof of validity.
 */
public class SPVNode extends Node {

    //Constants
    public static final String TAG = SPVNode.class.getSimpleName();

    //Attributes

    //Constructors
    public SPVNode(String name, int port) {
        super(name, port);
        setNodeEventCallback(callback);
    }

    @Override
    public boolean canMine() {
        return false;
    }

    @Override
    public boolean canRoute() {
        return true;
    }

    @Override
    public boolean hasCompleteBlockchain() {
        return false;
    }

    @Override
    public boolean hasWallet() {
        return true;
    }

    //Callbacks

    NodeEventCallback callback = new NodeEventCallback() {

        @Override
        public void onMessageSent(Protocol message) {

        }

        @Override
        public Protocol onProtocolRequestReceived(Protocol message) {
            return executor.resolveProtocolRequest(message);
        }

        @Override
        public void onProtocolResponseReceived(Protocol message) {
            executor.resolveProtocolResponse(message);
        }

        @Override
        public void onError(int errorCode, String errorMessage) {

        }

    };

}
