package com.github.jolice.metro.data.deserializer;

import com.google.gson.stream.JsonReader;

public interface GsonDeserializer<T> {

    T deserialize(JsonReader gson);

}
