package com.hiquanta.data.repository.datasource.user;

import android.content.Context;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.entity.UserEntity;


import com.hiquanta.data.net.RestApiWrapper;


import java.util.List;


import rx.Observable;
import rx.functions.Action1;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class CloudUserDataStore implements UserDataStore {

    RestApiWrapper restApiWrapper;
    private final UserCache userCache;
    private final Context context;
    private final Action1<UserEntity> saveToCacheAction = userEntity -> {
        if (userEntity != null) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    public CloudUserDataStore(Context context,UserCache userCache) {
        this.userCache = userCache;
        restApiWrapper=new RestApiWrapper(context);
        this.context=context;

    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return this.restApiWrapper.userEntityList();
    }

    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        return this.restApiWrapper.userEntityById(userId).doOnNext(saveToCacheAction);
    }

}
