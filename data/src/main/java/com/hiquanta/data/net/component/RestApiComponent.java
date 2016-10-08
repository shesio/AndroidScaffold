package com.hiquanta.data.net.component;

import com.hiquanta.data.net.module.RestApiModule;
import com.hiquanta.data.repository.datasource.CloudUserDataStore;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hiquanta on 2016/10/8.
 */
@Singleton
@Component(
        modules = RestApiModule.class
)
public interface RestApiComponent {
    void inject(CloudUserDataStore cloudUserDataStore);
}
