package com.hiquanta.scaffold.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.hiquanta.scaffold.AppContext;
import com.hiquanta.scaffold.R;
import com.hiquanta.scaffold.internal.di.HasComponent;
import com.hiquanta.scaffold.internal.di.components.AppIntroComponent;
import com.hiquanta.scaffold.internal.di.modules.AppIntroModule;
import com.hiquanta.scaffold.navigation.Navigator;
import com.hiquanta.scaffold.presenter.AppIntroPresenter;
import com.hiquanta.scaffold.view.AppIntroView;
import com.hiquanta.scaffold.view.fragment.AppIntroSlide;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/11/7.
 */

public class AppIntroActivity extends BaseIntro implements AppIntroView,HasComponent<AppIntroComponent> {

    @Inject
    Navigator navigator;
    @Inject
    AppIntroPresenter appIntroPresenter;
    private AppIntroComponent appIntroComponent;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, LoginActivity.class);

        return callingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroSlide.newInstance(R.layout.activity_appintro));
        addSlide(AppIntroSlide.newInstance(R.layout.activity_appintro2));
        addSlide(AppIntroSlide.newInstance(R.layout.activity_appintro3));
        addSlide(AppIntroSlide.newInstance(R.layout.activity_appintro4));
        this.initializeInjector();
        this.appIntroPresenter.setView(this);

    }
    private void initializeInjector() {
        this.appIntroComponent = AppContext.get().getApplicationComponent().plus(new AppIntroModule());
        this.appIntroComponent.inject(this);
    }
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        appIntroPresenter.onDonePressed();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        appIntroPresenter.onSkipPressed();
    }

    public void getStarted(View v) {
        appIntroPresenter.getStarted();
    }

    @Override
    public void navigateToLogin() {
        navigator.navigateToLogin(this);
        finish();
    }

    @Override
    public AppIntroComponent getComponent() {
        return appIntroComponent;
    }
}
