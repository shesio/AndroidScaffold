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

public class GetUserDetails extends UseCase{
    private final int userId;
    private final UserRepository userRepository;
    @Inject
    public GetUserDetails(int userId, UserRepository userRepository,
            ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userId = userId;
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.user(this.userId);
    }



}
