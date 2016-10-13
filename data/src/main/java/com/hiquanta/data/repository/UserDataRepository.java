package com.hiquanta.data.repository;

import com.hiquanta.data.cache.CacheProviders;
import com.hiquanta.data.entity.UserEntity;
import com.hiquanta.data.entity.mapper.UserEntityDataMapper;
import com.hiquanta.data.repository.datasource.user.UserDataStore;
import com.hiquanta.data.repository.datasource.user.UserDataStoreFactory;
import com.hiquanta.domain.User;
import com.hiquanta.domain.repository.UserRepository;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;
    private final CacheProviders cacheProviders;

    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory,
                              UserEntityDataMapper userEntityDataMapper,CacheProviders cacheProviders) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
        this.cacheProviders=cacheProviders;
    }
    @Override
    public Observable<List<User>> users() {
        final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
      //  return userDataStore.userEntityList().map(this.userEntityDataMapper::transform);
        return cacheProviders.userEntityList(userDataStore.userEntityList(),new DynamicKey("test"),new EvictDynamicKey(true)).map(new Func1<Reply<List<UserEntity>>, List<User>>() {
            @Override
            public List<User> call(Reply<List<UserEntity>> listReply) {
                return userEntityDataMapper.transform(listReply.getData());

            }
        });
    }

    @Override
    public Observable<User> user(int userId) {
        final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.userEntityDetails(userId).map(this.userEntityDataMapper::transform);
    }
}
