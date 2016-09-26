package com.hiquanta.scaffold.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.hiquanta.scaffold.AppContext;
import com.hiquanta.scaffold.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/9/26.
 */

public abstract class BaseActivity extends Activity {

    @Inject
    Navigator navigator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.get().getApplicationComponent().inject(this);
    }

}
