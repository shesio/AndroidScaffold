package com.hiquanta.scaffold.internal.di.components;

import com.hiquanta.scaffold.internal.di.modules.ActivityModule;
import com.hiquanta.scaffold.internal.di.modules.UserModule;
import com.hiquanta.scaffold.view.fragment.UserDetailsFragment;
import com.hiquanta.scaffold.view.fragment.UserListFragment;

import dagger.Component;

/**
 * Created by hiquanta on 2016/9/26.
 */

@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {
    void inject(UserListFragment userListFragment);
    void inject(UserDetailsFragment userDetailsFragment);
}
