package com.hiquanta.domain.interactor;

import com.hiquanta.domain.executor.PostExecutionThread;
import com.hiquanta.domain.executor.ThreadExecutor;
import com.hiquanta.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class GetUserList extends UseCase{
    private final UserRepository userRepository;
    @Inject
    protected GetUserList(UserRepository userRepository,
            ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository=userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.users(isEvict());
    }




}
