package com.hiquanta.scaffold.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.hiquanta.scaffold.AppContext;
import com.hiquanta.scaffold.R;
import com.hiquanta.scaffold.internal.di.HasComponent;

import com.hiquanta.scaffold.internal.di.components.LoginComponent;
import com.hiquanta.scaffold.internal.di.modules.LoginModule;
import com.hiquanta.scaffold.presenter.LoginPresenter;
import com.hiquanta.scaffold.util.ToastUtil;
import com.hiquanta.scaffold.view.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hiquanta on 2016/10/10.
 */

public class LoginActivity extends BaseActivity implements HasComponent<LoginComponent>, LoginView {
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.passWord)
    EditText passWord;
    @BindView(R.id.rl_progress)
    FrameLayout rl_progress;
  private LoginComponent loginComponent;

    @Inject
    LoginPresenter loginPresenter;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, MainActivity.class);

        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        this.initializeInjector();
        this.loginPresenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loginPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.destroy();
    }

    private void initializeInjector() {
//        this.loginComponent = DaggerLoginComponent.builder()
//                .applicationComponent(getApplicationComponent())
//                .activityModule(getActivityModule())
//                .loginModule(new LoginModule())
//                .build();
//        this.loginComponent.inject(this);
        this.loginComponent=   AppContext.get().getApplicationComponent().plus(new LoginModule());
        this.loginComponent.inject(this);
    }

    @OnClick(R.id.login)
    void Login() {
        loginPresenter.onLoginClick(userName.getText().toString(), passWord.getText().toString());
    }

    @Override
    public LoginComponent getComponent() {
        return loginComponent;
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        ToastUtil.showLong(message);
    }

    @Override
    public Context context() {
        return this.getApplicationContext();
    }


    @Override
    public void navigateToHome() {
        navigator.navigateToHome(this);
    }
}
