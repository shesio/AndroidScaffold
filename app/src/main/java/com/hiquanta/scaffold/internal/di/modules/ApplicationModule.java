package com.hiquanta.scaffold.internal.di.modules;

import android.content.Context;

import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.cache.UserCacheImpl;
import com.hiquanta.data.executor.JobExecutor;
import com.hiquanta.data.repository.UserDataRepository;
import com.hiquanta.scaffold.AppContext;
import com.hiquanta.scaffold.UIThread;
import com.hiquanta.scaffold.executor.PostExecutionThread;
import com.hiquanta.scaffold.executor.ThreadExecutor;
import com.hiquanta.scaffold.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}
