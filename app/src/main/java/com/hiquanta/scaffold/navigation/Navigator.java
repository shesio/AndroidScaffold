package com.hiquanta.scaffold.navigation;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class Navigator {
    @Inject
    public Navigator() {

    }

    public void navigateToUserList(Context context) {
        if (context != null) {
            Toast.makeText(context,"navigateToUserList",Toast.LENGTH_LONG).show();
        }
    }

    public void navigateToUserDetails(Context context) {
        if (context != null) {
            Toast.makeText(context,"navigateToUserDetails",Toast.LENGTH_LONG).show();
        }
    }
}
