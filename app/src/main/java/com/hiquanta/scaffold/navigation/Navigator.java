package com.hiquanta.scaffold.navigation;

import android.content.Context;
import android.content.Intent;

import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.view.activity.AppIntroActivity;
import com.hiquanta.scaffold.view.activity.LoginActivity;
import com.hiquanta.scaffold.view.activity.MainActivity;
import com.hiquanta.scaffold.view.activity.UserDetailsActivity;
import com.hiquanta.scaffold.view.activity.UserListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class Navigator {

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
    public void navigateToHome(Context context) {
        if (context != null) {
            Intent intentToLaunch = LoginActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
    public void navigateToLogin(Context context) {
        if (context != null) {
            Intent intentToLaunch = AppIntroActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
