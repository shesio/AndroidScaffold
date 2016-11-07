package com.hiquanta.scaffold.internal.di.components;

import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.internal.di.modules.ActivityModule;
import com.hiquanta.scaffold.internal.di.modules.AppIntroModule;
import com.hiquanta.scaffold.view.activity.AppIntroActivity;

import dagger.Subcomponent;

/**
 * Created by hiquanta on 2016/11/7.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, AppIntroModule.class})
public interface AppIntroComponent {
    void inject(AppIntroActivity appIntroActivity);
}
