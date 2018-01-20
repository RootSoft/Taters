package com.rootsoft.taters.models.protocols;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rootsoft.taters.models.protocols.messages.ProtocolMessage;
import com.rootsoft.taters.models.protocols.messages.VerackMessage;
import com.rootsoft.taters.models.protocols.messages.VersionMessage;
import com.rootsoft.taters.utils.RuntimeTypeAdapterFactory;

public class ProtocolSerializer {

    //Constants
    public static final String TAG = ProtocolSerializer.class.getSimpleName();

    //Attributes
    private Gson gson;

    public ProtocolSerializer() {
        RuntimeTypeAdapterFactory<ProtocolMessage> adapter = RuntimeTypeAdapterFactory
                        .of(ProtocolMessage.class)
                        .registerSubtype(VersionMessage.class)
                        .registerSubtype(VerackMessage.class);

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(adapter)
                .create();
    }

    public String serialize(ProtocolMessage message) {
        return gson.toJson(message, ProtocolMessage.class);
    }

    public ProtocolMessage deserialize(String message) {
        return gson.fromJson(message, ProtocolMessage.class);
    }
}
