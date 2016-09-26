package com.hiquanta.data.repository.datasource;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class DiskUserDataStore implements UserDataStore {
    private final UserCache userCache;
    DiskUserDataStore(UserCache userCache) {
        this.userCache = userCache;
    }
    @Override
    public Observable<List<UserEntity>> userEntityList() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        return this.userCache.get(userId);
    }
}
