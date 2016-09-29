package com.hiquanta.scaffold.internal.di.modules;

import android.app.Activity;

import com.hiquanta.scaffold.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Module
public class ActivityModule {
    private final Activity activity;
    public ActivityModule(Activity activity) {
        this.activity = activity;
    }
    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }
}
