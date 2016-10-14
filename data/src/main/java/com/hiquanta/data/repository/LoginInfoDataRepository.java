package com.hiquanta.data.repository;



import com.hiquanta.data.cache.CacheProviders;
import com.hiquanta.data.entity.mapper.LoginInfoEntityDataMapper;
import com.hiquanta.data.net.RestApiWrapper;

import com.hiquanta.domain.LoginInfo;

import com.hiquanta.domain.repository.LoginInfoRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created by hiquanta on 2016/10/10.
 */
@Singleton
public class LoginInfoDataRepository implements LoginInfoRepository {
    private final CacheProviders cacheProviders;
    private final RestApiWrapper restApiWrapper;
    private final LoginInfoEntityDataMapper loginInfoEntityDataMapper;
    @Inject
    public LoginInfoDataRepository(RestApiWrapper restApiWrapper,CacheProviders cacheProviders,
                                   LoginInfoEntityDataMapper loginInfoEntityDataMapper) {
        this.restApiWrapper=restApiWrapper;
        this.cacheProviders=cacheProviders;
        this.loginInfoEntityDataMapper = loginInfoEntityDataMapper;
    }

    @Override
    public Observable<LoginInfo>LoginInfo(String userName, String passWord) {
        return restApiWrapper.doLogin(2).map(this.loginInfoEntityDataMapper::transform);
    }
}
