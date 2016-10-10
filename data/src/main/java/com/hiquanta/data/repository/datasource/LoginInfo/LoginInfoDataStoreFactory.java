package com.hiquanta.data.repository.datasource.LoginInfo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.repository.datasource.user.CloudUserDataStore;
import com.hiquanta.data.repository.datasource.user.DiskUserDataStore;
import com.hiquanta.data.repository.datasource.user.UserDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Singleton
public class LoginInfoDataStoreFactory {
    private final Context context;
//    private final UserCache userCache;

    @Inject
    public LoginInfoDataStoreFactory(@NonNull Context context) {
        this.context = context.getApplicationContext();
        //this.userCache = userCache;
    }

    public LoginInfoDataStore create(int userId) {
        LoginInfoDataStore loginInfoDataStore;

//        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
//            userDataStore = new DiskUserDataStore(this.userCache);
//        } else {
        loginInfoDataStore = createCloudDataStore();
        //   }

        return loginInfoDataStore;
    }

    public LoginInfoDataStore createCloudDataStore() {
        //  UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
        // RestApi restApi = new RestApiImpl(this.context, )userEntityJsonMapper;

        return new CloudLoginInfoDataStore(context);
    }
}
