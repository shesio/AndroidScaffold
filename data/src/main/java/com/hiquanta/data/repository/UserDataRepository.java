package com.hiquanta.data.repository;

import com.hiquanta.data.entity.mapper.UserEntityDataMapper;
import com.hiquanta.data.repository.datasource.user.UserDataStore;
import com.hiquanta.data.repository.datasource.user.UserDataStoreFactory;
import com.hiquanta.domain.User;
import com.hiquanta.domain.repository.UserRepository;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory,
                              UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }
    @Override
    public Observable<List<User>> users() {
        final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.userEntityList().map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<User> user(int userId) {
        final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.userEntityDetails(userId).map(this.userEntityDataMapper::transform);
    }
}
