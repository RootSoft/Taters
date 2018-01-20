package com.rootsoft.taters.models.protocols.messages;

import com.rootsoft.taters.models.protocols.ProtocolType;

/**
 * This is sent as a request to get information about known peers.
 */
public class GetAddressProtocol extends Protocol {

    //Constants
    public static final String TAG = GetAddressProtocol.class.getSimpleName();

    //Attributes

    //Constructors
    public GetAddressProtocol() {
        super(ProtocolType.GET_ADDR);
    }

    //Properties

}
