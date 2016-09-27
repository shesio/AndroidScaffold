package com.hiquanta.scaffold.internal.di.modules;

import com.hiquanta.scaffold.executor.PostExecutionThread;
import com.hiquanta.scaffold.executor.ThreadExecutor;
import com.hiquanta.scaffold.interactor.GetUserDetails;
import com.hiquanta.scaffold.interactor.GetUserList;
import com.hiquanta.scaffold.interactor.UseCase;
import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.repository.UserRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Module
public class UserModule {
    private int userId = -1;

    public UserModule() {}

    public UserModule(int userId) {
        this.userId = userId;
    }
    @Provides
    @Named("userList")
    UseCase provideGetUserListUseCase(
            GetUserList getUserList) {
        return getUserList;
    }
    @Provides
    UseCase provideGetUserDetailsUseCase(
            UserRepository userRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetUserDetails(userId, userRepository, threadExecutor, postExecutionThread);
    }
}
