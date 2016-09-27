package com.hiquanta.scaffold.presenter;

import android.support.annotation.NonNull;

import com.hiquanta.scaffold.User;
import com.hiquanta.scaffold.exception.DefaultErrorBundle;
import com.hiquanta.scaffold.exception.ErrorBundle;
import com.hiquanta.scaffold.exception.ErrorMessageFactory;
import com.hiquanta.scaffold.interactor.DefaultSubscriber;
import com.hiquanta.scaffold.interactor.UseCase;
import com.hiquanta.scaffold.mapper.UserModelDataMapper;
import com.hiquanta.scaffold.model.UserModel;
import com.hiquanta.scaffold.view.UserDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UserDetailsPresenter implements Presenter {
    private UserDetailsView viewDetailsView;

    private final UseCase getUserDetailsUseCase;
    private final UserModelDataMapper userModelDataMapper;

    @Inject
    public UserDetailsPresenter( UseCase getUserDetailsUseCase,
                                UserModelDataMapper userModelDataMapper) {
        this.getUserDetailsUseCase = getUserDetailsUseCase;
        this.userModelDataMapper = userModelDataMapper;
    }
    public void setView( UserDetailsView view) {
        this.viewDetailsView = view;
    }
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getUserDetailsUseCase.unsubscribe();
        this.viewDetailsView = null;
    }
    public void initialize() {
        this.loadUserDetails();
    }
    private void loadUserDetails() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserDetails();
    }
    private void showViewLoading() {
        this.viewDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.viewDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.viewDetailsView.hideRetry();
    }
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(),
                errorBundle.getException());
        this.viewDetailsView.showError(errorMessage);
    }

    private void showUserDetailsInView(User user) {
        final UserModel userModel = this.userModelDataMapper.transform(user);
        this.viewDetailsView.renderUser(userModel);
    }

    private void getUserDetails() {
        this.getUserDetailsUseCase.execute(new UserDetailsSubscriber());
    }
    private final class UserDetailsSubscriber extends DefaultSubscriber<User> {

        @Override public void onCompleted() {
            UserDetailsPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            UserDetailsPresenter.this.hideViewLoading();
            UserDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserDetailsPresenter.this.showViewRetry();
        }

        @Override public void onNext(User user) {
            UserDetailsPresenter.this.showUserDetailsInView(user);
        }
    }
}
