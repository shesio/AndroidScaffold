package com.hiquanta.scaffold.mapper;



import com.hiquanta.domain.LoginInfo;
import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.model.LoginInfoModel;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/9/26.
 */
@PerActivity
public class LoginInfoModelDataMapper {
    @Inject
    public LoginInfoModelDataMapper() {
    }

    public LoginInfoModel transform(LoginInfo loginInfo) {
        if (loginInfo == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        LoginInfoModel loginInfoModel = new LoginInfoModel(loginInfo.getUserId());
        loginInfoModel.setCoverUrl(loginInfo.getCoverUrl());
        loginInfoModel.setFullName(loginInfo.getFullName());
        loginInfoModel.setEmail(loginInfo.getEmail());
        loginInfoModel.setDescription(loginInfo.getDescription());
        loginInfoModel.setFollowers(loginInfo.getFollowers());

        return loginInfoModel;
    }


}
