package com.hiquanta.data.repository.datasource.LoginInfo;

import android.content.Context;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.entity.LoginInfoEntity;
import com.hiquanta.data.net.RestApiWrapper;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by hiquanta on 2016/10/10.
 */

public class CloudLoginInfoDataStore implements LoginInfoDataStore {
    RestApiWrapper restApiWrapper;
   // private final UserCache userCache;
    private final Context context;
//    private final Action1<LoginInfoEntity> saveToCacheAction = loginInfoEntity -> {
//        if (loginInfoEntity != null) {
//            CloudLoginInfoDataStore.this.userCache.put(loginInfoEntity);
//        }
//    };

    public CloudLoginInfoDataStore(Context context) {
      //  this.userCache = userCache;
        restApiWrapper=new RestApiWrapper(context);
        this.context=context;

    }
    @Override
    public Observable<LoginInfoEntity> LoginInfoEntityDetails(int userId) {
        return restApiWrapper.doLogin(userId);
    }
}
