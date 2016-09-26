package com.hiquanta.data.repository.datasource;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.entity.UserEntity;
import com.hiquanta.data.net.RestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class CloudUserDataStore implements UserDataStore {
    private final RestApi restApi;
    private final UserCache userCache;

    private final Action1<UserEntity> saveToCacheAction = userEntity -> {
        if (userEntity != null) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    CloudUserDataStore(RestApi restApi, UserCache userCache) {
        this.restApi = restApi;
        this.userCache = userCache;
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
