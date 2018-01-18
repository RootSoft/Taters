package com.rootsoft.taters.models.commands;

import com.rootsoft.taters.models.protocols.ProtocolMessage;

public interface ProtocolCommand {

    /**
     * Executes the command.
     */
    void execute();

    /**
     * Sends a response to the requester.
     * @return The protocol message to sent back. This can be null.
     */
    ProtocolMessage response();
}
