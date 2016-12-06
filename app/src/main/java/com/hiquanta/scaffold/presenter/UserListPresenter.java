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
import com.hiquanta.scaffold.view.UserListView;
import com.orhanobut.logger.Logger;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.rx_cache.Reply;

/**
 * Created by hiquanta on 2016/9/26.
 */
@PerActivity
public class UserListPresenter implements Presenter {
    private UserListView viewListView;

    private final UseCase getUserListUseCase;

    @Inject
    public UserListPresenter(
            @Named("userList")
                    UseCase getUserListUserCase) {
        this.getUserListUseCase = getUserListUserCase;
    }

    public void setView(UserListView view) {
        this.viewListView = view;
    }
    @Override
    public void resume() {

    }
    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getUserListUseCase.unsubscribe();
        this.viewListView = null;
    }

    public void initialize() {
        this.loadUserList();
    }

    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    public void onUserClicked(UserModel userModel) {
        this.viewListView.viewUser(userModel);
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showUsersCollectionInView(List<User> usersCollection) {
        final Collection<UserModel> userModelsCollection = MapperUtil.mapList(usersCollection,UserModel.class);
        this.viewListView.renderUserList(userModelsCollection);
    }

    private void getUserList() {
        getUserListUseCase.setEvict(false);
        this.getUserListUseCase.execute(new UserListSubscriber());
    }

    private final class UserListSubscriber extends DefaultSubscriber<Reply<List<User>>> {

        @Override
        public void onCompleted() {
            UserListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Logger.e(e,"onError");
            UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Reply<List<User>> users) {
            viewListView.dataFrom(users.getSource().name());
            UserListPresenter.this.showUsersCollectionInView(users.getData());
        }
    }
}
