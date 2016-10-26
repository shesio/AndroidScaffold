package com.hiquanta.scaffold.presenter;


import com.hiquanta.domain.User;
import com.hiquanta.domain.exception.DefaultErrorBundle;
import com.hiquanta.domain.exception.ErrorBundle;
import com.hiquanta.domain.interactor.DefaultSubscriber;
import com.hiquanta.domain.interactor.UseCase;
import com.hiquanta.domain.mapper.MapperUtil;
import com.hiquanta.scaffold.exception.ErrorMessageFactory;

import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.model.UserModel;
import com.hiquanta.scaffold.view.UserDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

import io.rx_cache.Reply;

/**
 * Created by hiquanta on 2016/9/26.
 */
@PerActivity
public class UserDetailsPresenter implements Presenter {
    private UserDetailsView viewDetailsView;

    private final UseCase getUserDetailsUseCase;

    @Inject
    public UserDetailsPresenter(@Named("userDetails") UseCase getUserDetailsUseCase) {
        this.getUserDetailsUseCase = getUserDetailsUseCase;
    }

    public void setView(UserDetailsView view) {
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
        final UserModel userModel =  MapperUtil.map(user,UserModel.class);
        this.viewDetailsView.renderUser(userModel);
    }

    private void getUserDetails() {
        this.getUserDetailsUseCase.execute(new UserDetailsSubscriber());
    }

    private final class UserDetailsSubscriber extends DefaultSubscriber<Reply<User>> {

        @Override
        public void onCompleted() {
            UserDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserDetailsPresenter.this.hideViewLoading();
            UserDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Reply<User> user) {
            viewDetailsView.dataFrom(user.getSource().name());
            UserDetailsPresenter.this.showUserDetailsInView(user.getData());
        }
    }
}
