package com.hiquanta.scaffold.navigation;

import android.content.Context;
import android.content.Intent;

import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.view.activity.UserDetailsActivity;
import com.hiquanta.scaffold.view.activity.UserListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class Navigator {
    @Inject
    public Navigator() {

    }

    public void navigateToUserList(Context context) {
        if (context != null) {
            Intent intentToLaunch = UserListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToUserDetails(Context context, int userId) {
        if (context != null) {
            Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId);
            context.startActivity(intentToLaunch);
        }
    }
}
