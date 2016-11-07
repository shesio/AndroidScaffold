package com.hiquanta.scaffold.internal.di.components;

import com.hiquanta.data.net.module.RestApiModule;
import com.hiquanta.scaffold.internal.di.modules.AppIntroModule;
import com.hiquanta.scaffold.internal.di.modules.ApplicationModule;

import com.hiquanta.scaffold.internal.di.modules.LoginModule;
import com.hiquanta.scaffold.internal.di.modules.UserModule;
import com.hiquanta.scaffold.view.activity.AppIntroActivity;
import com.hiquanta.scaffold.view.activity.BaseActivity;
import com.hiquanta.scaffold.view.activity.BaseIntro;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RestApiModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseIntro baseIntro);
    LoginComponent plus(LoginModule loginModule);

    UserComponent plus(UserModule userModule);

    AppIntroComponent plus(AppIntroModule appIntroModule);
}
