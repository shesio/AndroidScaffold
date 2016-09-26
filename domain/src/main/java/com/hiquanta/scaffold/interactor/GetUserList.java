package com.hiquanta.scaffold.interactor;

import com.hiquanta.scaffold.executor.PostExecutionThread;
import com.hiquanta.scaffold.executor.ThreadExecutor;
import com.hiquanta.scaffold.repository.UserRepository;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class GetUserList extends UseCase {
    private final UserRepository userRepository;
    protected GetUserList(UserRepository userRepository,
            ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository=userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.users();
    }
}
