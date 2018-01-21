package com.rootsoft.taters.models.node;

import java.io.Serializable;

public class NodeAddress implements Serializable {

    //Constants
    public static final String TAG = NodeAddress.class.getSimpleName();

    //Attributes
    private String peerId;
    private String host;
    private String address;
    private int port;

    //Constructors
    public NodeAddress(String peerId, String host, String address, int port) {
        this.peerId = peerId;
        this.host = host;
        this.address = address;
        this.port = port;
    }

    //Properties


    public String getPeerId() {
        return peerId;
    }

    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "NodeAddress{" +
                "peerId='" + peerId + '\'' +
                ", host='" + host + '\'' +
                ", address='" + address + '\'' +
                ", port=" + port +
                '}';
    }
}
