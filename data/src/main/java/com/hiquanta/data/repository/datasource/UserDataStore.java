package com.hiquanta.data.repository.datasource;

import com.hiquanta.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserDataStore {
    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntityDetails(final int userId);
}
