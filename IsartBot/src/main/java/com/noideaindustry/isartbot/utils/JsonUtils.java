package com.noideaindustry.isartbot.utils;

import com.google.gson.JsonObject;

public class JsonUtils {
    private final JsonObject object;
    public JsonUtils(final JsonObject object) {
        this.object = object;
    }

    public String getMember(final String member) {
        return object.get(member).getAsString();
    }
}
