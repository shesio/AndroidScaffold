package com.hiquanta.data.cache;

import com.hiquanta.data.entity.UserEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by hiquanta on 2016/10/13.
 */

public interface CacheProviders {
    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<Reply<List<UserEntity>>> userEntityList(Observable<List<UserEntity>> userEntityListObservable, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<UserEntity>> userEntityDetails(Observable<UserEntity> userEntityObservable, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
