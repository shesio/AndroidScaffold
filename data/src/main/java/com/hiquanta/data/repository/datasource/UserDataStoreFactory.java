package com.hiquanta.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.entity.mapper.UserEntityJsonMapper;
import com.hiquanta.data.net.RestApi;
import com.hiquanta.data.net.RestApiImpl;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UserDataStoreFactory {
    private final Context context;
    private final UserCache userCache;
    @Inject
    public UserDataStoreFactory(@NonNull Context context, @NonNull UserCache userCache) {
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }
    public UserDataStore create(int userId) {
        UserDataStore userDataStore;

        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
            userDataStore = new DiskUserDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }
    public UserDataStore createCloudDataStore() {
        UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
        RestApi restApi = new RestApiImpl(this.context, userEntityJsonMapper);

        return new CloudUserDataStore(restApi, this.userCache);
    }
}
