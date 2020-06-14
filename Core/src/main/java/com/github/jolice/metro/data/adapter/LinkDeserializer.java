package com.github.jolice.metro.data.adapter;

import com.github.jolice.metro.model.link.Link;
import com.google.gson.*;

import java.lang.reflect.Type;

public class LinkDeserializer implements JsonDeserializer<Link> {

    private Gson gson = new Gson();

    @Override
    public Link deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Link link = gson.fromJson(jsonElement, Link.class);
        link.setTo(link.getTo() - 1);
        link.setFrom(link.getFrom() - 1);
        return link;
    }
}
