package com.hiquanta.scaffold.interactor;

import com.hiquanta.scaffold.executor.PostExecutionThread;
import com.hiquanta.scaffold.executor.ThreadExecutor;
import com.hiquanta.scaffold.repository.UserRepository;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class GetUserDetails extends UseCase {
    private final int userId;
    private final UserRepository userRepository;
    protected GetUserDetails(int userId, UserRepository userRepository,
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
