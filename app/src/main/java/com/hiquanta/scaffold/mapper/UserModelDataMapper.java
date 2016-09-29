package com.hiquanta.scaffold.mapper;


import com.hiquanta.domain.User;
import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/9/26.
 */
@PerActivity
public class UserModelDataMapper {
    @Inject
    public UserModelDataMapper() {
    }

    public UserModel transform(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        UserModel userModel = new UserModel(user.getUserId());
        userModel.setCoverUrl(user.getCoverUrl());
        userModel.setFullName(user.getFullName());
        userModel.setEmail(user.getEmail());
        userModel.setDescription(user.getDescription());
        userModel.setFollowers(user.getFollowers());

        return userModel;
    }

    public Collection<UserModel> transform(Collection<User> usersCollection) {
        Collection<UserModel> userModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (User user : usersCollection) {
                userModelsCollection.add(transform(user));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
