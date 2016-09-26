package com.hiquanta.data.cache;

import com.hiquanta.data.entity.UserEntity;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserCache {
    Observable<UserEntity> get(final int userId);

    void put(UserEntity userEntity);

    boolean isCached(final int userId);

    boolean isExpired();

    void evictAll();
}
