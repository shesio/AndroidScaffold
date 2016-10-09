//package com.hiquanta.data.entity.mapper;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//import com.google.gson.reflect.TypeToken;
//import com.hiquanta.data.entity.UserEntity;
//
//import java.lang.reflect.Type;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
///**
// * Created by hiquanta on 2016/9/26.
// */
//
//public class UserEntityJsonMapper {
//    private final Gson gson;
//
//
//    public UserEntityJsonMapper() {
//        this.gson = new Gson();
//    }
//    public UserEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
//        try {
//            Type userEntityType = new TypeToken<UserEntity>() {}.getType();
//            UserEntity userEntity = this.gson.fromJson(userJsonResponse, userEntityType);
//
//            return userEntity;
//        } catch (JsonSyntaxException jsonException) {
//            throw jsonException;
//        }
//    }
//    public List<UserEntity> transformUserEntityCollection(String userListJsonResponse)
//            throws JsonSyntaxException {
//
//        List<UserEntity> userEntityCollection;
//        try {
//            Type listOfUserEntityType = new TypeToken<List<UserEntity>>() {}.getType();
//            userEntityCollection = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
//
//            return userEntityCollection;
//        } catch (JsonSyntaxException jsonException) {
//            throw jsonException;
//        }
//    }
//}
