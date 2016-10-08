package com.hiquanta.data.repository.datasource;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.entity.UserEntity;

import com.hiquanta.data.net.RestApi;

import com.hiquanta.data.net.component.DaggerRestApiComponent;
import com.hiquanta.data.net.module.RestApiModule;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class CloudUserDataStore implements UserDataStore {
    @Inject
     RestApi restApi;
    private final UserCache userCache;

    private final Action1<UserEntity> saveToCacheAction = userEntity -> {
        if (userEntity != null) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    public CloudUserDataStore( UserCache userCache) {
        this.userCache = userCache;
        DaggerRestApiComponent.builder().restApiModule(new RestApiModule()).build().inject(this);
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return this.restApi.userEntityList();
    }

    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        return this.restApi.userEntityById(userId).doOnNext(saveToCacheAction);
    }
}
