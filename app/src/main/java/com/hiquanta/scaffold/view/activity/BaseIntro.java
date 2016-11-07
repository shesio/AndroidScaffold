package com.hiquanta.scaffold.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.hiquanta.scaffold.AppContext;

/**
 * Created by hiquanta on 2016/11/7.
 */

public class BaseIntro extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.get().getApplicationComponent().inject(this);
    }
}
