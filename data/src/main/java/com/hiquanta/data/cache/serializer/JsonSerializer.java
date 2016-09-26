package com.hiquanta.data.cache.serializer;

import com.google.gson.Gson;
import com.hiquanta.data.entity.UserEntity;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class JsonSerializer {
    private final Gson gson = new Gson();
    @Inject
    public JsonSerializer() {}
    public String serialize(UserEntity userEntity) {
        String jsonString = gson.toJson(userEntity, UserEntity.class);
        return jsonString;
    }
    public UserEntity deserialize(String jsonString) {
        UserEntity userEntity = gson.fromJson(jsonString, UserEntity.class);
        return userEntity;
    }
}
