package com.rootsoft.taters.models.node;

import net.tomp2p.peers.Number160;

import java.io.Serializable;

public class NodeAddress implements Serializable {

    //Constants
    public static final String TAG = NodeAddress.class.getSimpleName();

    //Attributes
    private Number160 peerId;
    private String hostName;
    private String hostAddress;
    private int tcpPort;
    private int udpPort;

    //Constructors
    public NodeAddress(Number160 peerId, String hostName, String address, int tcpPort, int udpPort) {
        this.peerId = peerId;
        this.hostName = hostName;
        this.hostAddress = address;
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
    }

    //Properties


    public Number160 getPeerId() {
        return peerId;
    }

    public void setPeerId(Number160 peerId) {
        this.peerId = peerId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    @Override
    public String toString() {
        return "NodeAddress{" +
                "peerId=" + peerId +
                ", hostName='" + hostName + '\'' +
                ", hostAddress='" + hostAddress + '\'' +
                ", tcpPort=" + tcpPort +
                ", udpPort=" + udpPort +
                '}';
    }
}
