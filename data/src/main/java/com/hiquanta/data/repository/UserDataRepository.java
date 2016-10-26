package com.hiquanta.data.repository;

import com.hiquanta.data.cache.CacheProviders;
import com.hiquanta.data.net.RestApiWrapper;
import com.hiquanta.domain.User;
import com.hiquanta.domain.mapper.MapperUtil;
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


    private final CacheProviders cacheProviders;
    private final RestApiWrapper restApiWrapper;

    @Inject
    public UserDataRepository(RestApiWrapper restApiWrapper,  CacheProviders cacheProviders) {
        this.restApiWrapper = restApiWrapper;
        this.cacheProviders = cacheProviders;
    }

    @Override
    public Observable<Reply<List<User>>> users(boolean isEvict) {
        return cacheProviders.userEntityList(restApiWrapper.userEntityList(), new DynamicKey("test"), new EvictDynamicKey(isEvict))
        .map(listReply -> MapperUtil.mapList(listReply,User.class));
    }

    @Override
    public Observable<Reply<User>> user(int userId) {
        return cacheProviders.userEntityDetails(restApiWrapper.userEntityById(userId), new DynamicKey(userId), new EvictDynamicKey(false))
                .map(userEntityReply -> MapperUtil.map(userEntityReply,User.class));
    }
}
