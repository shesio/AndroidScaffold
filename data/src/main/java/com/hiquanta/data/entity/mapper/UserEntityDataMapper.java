package com.hiquanta.data.entity.mapper;

import com.hiquanta.data.entity.UserEntity;
import com.hiquanta.domain.User;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.rx_cache.Reply;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Singleton
public class UserEntityDataMapper {
    @Inject
    public UserEntityDataMapper() {}
    public User transform(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User(userEntity.getUserId());
            user.setCoverUrl(userEntity.getCoverUrl());
            user.setFullName(userEntity.getFullname());
            user.setDescription(userEntity.getDescription());
            user.setFollowers(userEntity.getFollowers());
            user.setEmail(userEntity.getEmail());
        }

        return user;
    }
    public Reply<User> transformUser(Reply<UserEntity> userEntityReply) {
        User user = null;
        UserEntity userEntity=userEntityReply.getData();
        if (userEntity != null) {
            user = new User(userEntity.getUserId());
            user.setCoverUrl(userEntity.getCoverUrl());
            user.setFullName(userEntity.getFullname());
            user.setDescription(userEntity.getDescription());
            user.setFollowers(userEntity.getFollowers());
            user.setEmail(userEntity.getEmail());
        }
        Reply<User> replyUser=new Reply<>(user,userEntityReply.getSource(),false);
        return replyUser;
    }
    public Reply<List<User>> transformList(Reply<List<UserEntity>> userEntityCollection) {
        List<User> userList = new ArrayList<>(20);
        User user;
        for (UserEntity userEntity : userEntityCollection.getData()) {
            user = transform(userEntity);
            if (user != null) {
                userList.add(user);
            }
        }
        Reply<List<User>> replyUserList=new Reply<>(userList,userEntityCollection.getSource(),false);
        return replyUserList;
    }
}
