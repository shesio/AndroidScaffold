package com.hiquanta.domain;

import android.app.Application;

import com.hiquanta.domain.internal.di.components.ApplicationComponent;
import com.hiquanta.domain.internal.di.components.DaggerApplicationComponent;
import com.hiquanta.domain.internal.di.modules.ApplicationModule;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class AppContext extends Application {
    private ApplicationComponent applicationComponent;
    private static AppContext instance;
    public static AppContext get(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.initializeInjector();

    }
    private void initializeInjector() {
      this.applicationComponent= DaggerApplicationComponent.builder()
              .applicationModule(new ApplicationModule(this))
              .build();
    }
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
