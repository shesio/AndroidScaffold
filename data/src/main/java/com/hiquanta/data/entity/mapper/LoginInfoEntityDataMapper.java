package com.hiquanta.data.entity.mapper;



import com.hiquanta.data.entity.LoginInfoEntity;
import com.hiquanta.domain.LoginInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Singleton
public class LoginInfoEntityDataMapper {
    @Inject
    public LoginInfoEntityDataMapper() {}
    public LoginInfo transform(LoginInfoEntity loginInfoEntity) {
        LoginInfo loginInfo = null;
        if (loginInfoEntity != null) {
            loginInfo = new LoginInfo(loginInfoEntity.getUserId());
            loginInfo.setCoverUrl(loginInfoEntity.getCoverUrl());
            loginInfo.setFullName(loginInfoEntity.getFullname());
            loginInfo.setDescription(loginInfoEntity.getDescription());
            loginInfo.setFollowers(loginInfoEntity.getFollowers());
            loginInfo.setEmail(loginInfoEntity.getEmail());
        }

        return loginInfo;
    }

}
