package com.hiquanta.domain.interactor;

import com.hiquanta.domain.executor.PostExecutionThread;
import com.hiquanta.domain.executor.ThreadExecutor;
import com.hiquanta.domain.repository.LoginInfoRepository;
import com.hiquanta.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by hiquanta on 2016/10/10.
 */

public class GetLoginInfo extends UseCase  {

    private final int userId;
    private final LoginInfoRepository loginInfoRepository;



    @Inject
    public GetLoginInfo(int userId, LoginInfoRepository loginInfoRepository,
                          ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userId = userId;
        this.loginInfoRepository = loginInfoRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.loginInfoRepository.LoginInfo(this.userId);
    }
}
