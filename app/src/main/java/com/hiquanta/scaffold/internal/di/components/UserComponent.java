package com.hiquanta.scaffold.internal.di.components;

import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.internal.di.modules.ActivityModule;
import com.hiquanta.scaffold.internal.di.modules.UserModule;
import com.hiquanta.scaffold.view.fragment.UserDetailsFragment;
import com.hiquanta.scaffold.view.fragment.UserListFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by hiquanta on 2016/9/26.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {
    void inject(UserListFragment userListFragment);
    void inject(UserDetailsFragment userDetailsFragment);
}
