package com.hiquanta.scaffold.presenter;

import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.view.AppIntroView;
import com.hiquanta.scaffold.view.LoginView;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/11/7.
 */
@PerActivity
public class AppIntroPresenter implements Presenter {
    private AppIntroView appIntroView;

    @Inject
    public AppIntroPresenter() {

    }
    public void onDonePressed(){
        appIntroView.navigateToLogin();
    }
    public void onSkipPressed(){
        appIntroView.navigateToLogin();
    }
    public void getStarted(){
        appIntroView.navigateToLogin();
    }
    public void setView(AppIntroView view) {
        this.appIntroView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
