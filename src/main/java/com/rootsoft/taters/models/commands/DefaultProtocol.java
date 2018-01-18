package com.rootsoft.taters.models.commands;

import com.rootsoft.taters.models.protocols.ProtocolMessage;

public class DefaultProtocol implements ProtocolCommand {

    @Override
    public void execute() {
        System.out.println("Default behaviour");
    }

    @Override
    public ProtocolMessage response() {
        return null;
    }

}
