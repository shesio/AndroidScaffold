package com.hiquanta.scaffold.internal.di.modules;


import com.hiquanta.domain.executor.PostExecutionThread;
import com.hiquanta.domain.executor.ThreadExecutor;
import com.hiquanta.domain.interactor.GetUserDetails;
import com.hiquanta.domain.interactor.GetUserList;
import com.hiquanta.domain.interactor.UseCase;
import com.hiquanta.domain.repository.UserRepository;
import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Module
public class UserModule {
    private int userId = -1;
    private List<UserModel> usersCollection= new ArrayList<>();

    public UserModule() {
      //  usersCollection= new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            UserModel userModel=new UserModel(2);
//            userModel.setFullName("test");
//            usersCollection.add(userModel);
//        }
    }

    public UserModule(int userId) {
        this.userId = userId;
    }
    @Provides
    @PerActivity
    List<UserModel> provideUserCollection(){
        return usersCollection;
    }
    @Provides
    @PerActivity
    @Named("userList")
    UseCase provideGetUserListUseCase(
            GetUserList getUserList) {
        return getUserList;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetUserDetailsUseCase(
            UserRepository userRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetUserDetails(userId, userRepository, threadExecutor, postExecutionThread);
    }
}
