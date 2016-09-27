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
import com.hiquanta.scaffold.view.UserListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UserListPresenter implements Presenter {
    private UserListView viewListView;

    private final UseCase getUserListUseCase;
    private final UserModelDataMapper userModelDataMapper;

    @Inject
    public UserListPresenter(@Named("userList")UseCase getUserListUserCase,
                             UserModelDataMapper userModelDataMapper) {
        this.getUserListUseCase = getUserListUserCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView( UserListView view) {
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

    private void showUsersCollectionInView(Collection<User> usersCollection) {
        final Collection<UserModel> userModelsCollection =
                this.userModelDataMapper.transform(usersCollection);
        this.viewListView.renderUserList(userModelsCollection);
    }

    private void getUserList() {
        this.getUserListUseCase.execute(new UserListSubscriber());
    }
    private final class UserListSubscriber extends DefaultSubscriber<List<User>> {

        @Override public void onCompleted() {
            UserListPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();
        }

        @Override public void onNext(List<User> users) {
            UserListPresenter.this.showUsersCollectionInView(users);
        }
    }
}
