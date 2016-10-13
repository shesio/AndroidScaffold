package com.hiquanta.data.repository;

import com.hiquanta.data.cache.CacheProviders;
import com.hiquanta.data.entity.mapper.UserEntityDataMapper;
import com.hiquanta.data.net.RestApiWrapper;
import com.hiquanta.domain.User;
import com.hiquanta.domain.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserEntityDataMapper userEntityDataMapper;
    private final CacheProviders cacheProviders;
    private final RestApiWrapper restApiWrapper;

    @Inject
    public UserDataRepository(RestApiWrapper restApiWrapper, UserEntityDataMapper userEntityDataMapper, CacheProviders cacheProviders) {
        this.restApiWrapper = restApiWrapper;
        this.userEntityDataMapper = userEntityDataMapper;
        this.cacheProviders = cacheProviders;
    }

    @Override
    public Observable<Reply<List<User>>> users() {
        return cacheProviders.userEntityList(restApiWrapper.userEntityList(), new DynamicKey("test"), new EvictDynamicKey(false))
                .map(this.userEntityDataMapper::transformList);
    }

    @Override
    public Observable<Reply<User>> user(int userId) {
        return cacheProviders.userEntityDetails(restApiWrapper.userEntityById(userId), new DynamicKey(userId), new EvictDynamicKey(false)).map(this.userEntityDataMapper::transformUser);
    }
}
