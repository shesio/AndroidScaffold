package com.hiquanta.scaffold.presenter;

import com.hiquanta.data.repository.datasource.LoginInfo.LoginInfoDataStore;
import com.hiquanta.domain.LoginInfo;
import com.hiquanta.domain.User;
import com.hiquanta.domain.exception.DefaultErrorBundle;
import com.hiquanta.domain.exception.ErrorBundle;
import com.hiquanta.domain.interactor.DefaultSubscriber;
import com.hiquanta.domain.interactor.GetLoginInfo;
import com.hiquanta.domain.interactor.UseCase;
import com.hiquanta.scaffold.exception.ErrorMessageFactory;
import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.mapper.LoginInfoModelDataMapper;
import com.hiquanta.scaffold.util.Validator;
import com.hiquanta.scaffold.view.LoginView;


import javax.inject.Inject;


/**
 * Created by hiquanta on 2016/10/10.
 */
@PerActivity
public class LoginPresenter implements Presenter {
    private LoginView loginView;

    private final UseCase getLoginInfoUseCase;
    private final LoginInfoModelDataMapper loginInfoModelDataMapper;
    @Inject
    public LoginPresenter(UseCase getLoginInfoUseCase,LoginInfoModelDataMapper loginInfoModelDataMapper) {
        this.getLoginInfoUseCase = getLoginInfoUseCase;
        this.loginInfoModelDataMapper=loginInfoModelDataMapper;
    }

    public void setView(LoginView view) {
        this.loginView = view;
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

    public void onLoginClick(String userName, String passWord) {
        this.doLogin(userName, passWord);
    }

    private void doLogin(String userName, String passWord) {
        this.showViewLoading();
        validateCredentials(userName, passWord);
    }

    private void showViewLoading() {
        this.loginView.showLoading();
    }

    private void hideViewLoading() {
        this.loginView.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.loginView.context(),
                errorBundle.getException());
        this.loginView.showError(errorMessage);
    }

    private void validateCredentials(String username, String password) {
        if (Validator.vvalidateCredentials(username, password)) {
            ((GetLoginInfo)(this.getLoginInfoUseCase)).setArgs(username,password);
            this.getLoginInfoUseCase.execute(new LoginInfoSubscriber());
        } else {
            hideViewLoading();
            loginView.showError("Error");
        }
    }

    private final class LoginInfoSubscriber extends DefaultSubscriber<LoginInfo> {


        @Override
        public void onCompleted() {
            LoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            LoginPresenter.this.hideViewLoading();
            LoginPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));

        }

        @Override
        public void onNext(LoginInfo loginInfo) {

            loginView.navigateToHome();
        }
    }


}
