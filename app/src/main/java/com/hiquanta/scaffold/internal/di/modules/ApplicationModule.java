package com.hiquanta.scaffold.internal.di.modules;

import android.content.Context;

import com.hiquanta.data.cache.CacheProviders;
import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.cache.UserCacheImpl;
import com.hiquanta.data.executor.JobExecutor;
import com.hiquanta.data.net.RestApiWrapper;
import com.hiquanta.data.repository.LoginInfoDataRepository;
import com.hiquanta.data.repository.UserDataRepository;
import com.hiquanta.domain.executor.PostExecutionThread;
import com.hiquanta.domain.executor.ThreadExecutor;
import com.hiquanta.domain.repository.LoginInfoRepository;
import com.hiquanta.domain.repository.UserRepository;
import com.hiquanta.scaffold.AppContext;
import com.hiquanta.scaffold.UIThread;
import com.hiquanta.scaffold.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by hiquanta on 2016/9/26.
 */

@Module
public class ApplicationModule {
    private final AppContext application;

    public ApplicationModule(AppContext application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }
    @Provides
    @Singleton
    CacheProviders provideCacheProviders(Context context){
        return new RxCache.Builder()
                .persistence(context.getFilesDir(), new GsonSpeaker())
                .using(CacheProviders.class);
    }
    @Provides
    @Singleton
    RestApiWrapper provideRestApiWrapperProviders(Context context){
        return new RestApiWrapper(context);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
    @Provides
    @Singleton
    LoginInfoRepository provideLoginInfoRepository(LoginInfoDataRepository loginInfoDataRepository) {
        return loginInfoDataRepository;
    }
    @Provides
    @Singleton
    Navigator provideNavigator(){
        return new Navigator();
    }
}
