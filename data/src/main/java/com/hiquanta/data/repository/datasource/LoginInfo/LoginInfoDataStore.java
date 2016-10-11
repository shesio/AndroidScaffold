package com.hiquanta.data.repository.datasource.LoginInfo;

import com.hiquanta.data.entity.LoginInfoEntity;
import com.hiquanta.data.entity.UserEntity;
import com.hiquanta.domain.LoginInfo;

import rx.Observable;

/**
 * Created by hiquanta on 2016/10/10.
 */

public interface LoginInfoDataStore {
    Observable<LoginInfoEntity> LoginInfoEntityDetails(final String passWord,String userName);
    //Observable<LoginInfoEntity> LoginInfoEntityDetails(final int userId);
}
