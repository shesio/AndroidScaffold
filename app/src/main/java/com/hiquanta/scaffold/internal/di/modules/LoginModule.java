package com.hiquanta.scaffold.internal.di.modules;

import com.hiquanta.domain.executor.PostExecutionThread;
import com.hiquanta.domain.executor.ThreadExecutor;
import com.hiquanta.domain.interactor.GetLoginInfo;
import com.hiquanta.domain.interactor.GetUserList;
import com.hiquanta.domain.interactor.UseCase;
import com.hiquanta.domain.repository.LoginInfoRepository;
import com.hiquanta.domain.repository.UserRepository;
import com.hiquanta.scaffold.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hiquanta on 2016/10/10.
 */
@Module
public class LoginModule {
    private int userId = -1;

    public LoginModule() {}

    public LoginModule(int userId) {
        this.userId = userId;
    }
    @Provides
    @PerActivity
    UseCase provideGetLoginInfoUseCase(
            LoginInfoRepository loginInfoRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetLoginInfo(userId,loginInfoRepository,threadExecutor,postExecutionThread);
    }
}
