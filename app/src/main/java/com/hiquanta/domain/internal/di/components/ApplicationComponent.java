package com.hiquanta.domain.internal.di.components;

import android.content.Context;

import com.hiquanta.domain.executor.PostExecutionThread;
import com.hiquanta.domain.executor.ThreadExecutor;
import com.hiquanta.domain.internal.di.modules.ApplicationModule;
import com.hiquanta.domain.repository.UserRepository;
import com.hiquanta.domain.view.activity.BaseActivity;

import dagger.Component;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    UserRepository userRepository();
}
