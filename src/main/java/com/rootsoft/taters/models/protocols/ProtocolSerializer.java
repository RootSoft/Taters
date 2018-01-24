package com.rootsoft.taters.models.protocols;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rootsoft.taters.models.protocols.messages.*;
import com.rootsoft.taters.utils.RuntimeTypeAdapterFactory;

public class ProtocolSerializer {

    //Constants
    public static final String TAG = ProtocolSerializer.class.getSimpleName();

    //Attributes
    private Gson gson;

    public ProtocolSerializer() {
        RuntimeTypeAdapterFactory<Protocol> adapter = RuntimeTypeAdapterFactory
                        .of(Protocol.class)
                        .registerSubtype(VersionProtocol.class)
                        .registerSubtype(VerackProtocol.class)
                        .registerSubtype(GetAddressProtocol.class)
                        .registerSubtype(PingProtocol.class)
                        .registerSubtype(AddressProtocol.class);

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(adapter)
                .create();
    }

    public String serialize(Protocol message) {
        return gson.toJson(message, Protocol.class);
    }

    public Protocol deserialize(String message) {
        return gson.fromJson(message, Protocol.class);
    }
}
