package com.hiquanta.scaffold.internal.di.modules;

import android.content.Context;

import com.hiquanta.scaffold.AppContext;

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
}
