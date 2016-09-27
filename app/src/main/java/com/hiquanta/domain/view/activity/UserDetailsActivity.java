package com.hiquanta.domain.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.hiquanta.domain.R;
import com.hiquanta.domain.internal.di.HasComponent;
import com.hiquanta.domain.internal.di.components.DaggerUserComponent;
import com.hiquanta.domain.internal.di.components.UserComponent;
import com.hiquanta.domain.internal.di.modules.UserModule;
import com.hiquanta.domain.view.fragment.UserDetailsFragment;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UserDetailsActivity extends BaseActivity implements HasComponent<UserComponent> {
    private static final String INTENT_EXTRA_PARAM_USER_ID = "com.hiquanta.INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_USER_ID = "com.hiquanta.STATE_PARAM_USER_ID";

    public static Intent getCallingIntent(Context context, int userId) {
        Intent callingIntent = new Intent(context, UserDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
        return callingIntent;
    }

    private int userId;
    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }
    @Override protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(INSTANCE_STATE_PARAM_USER_ID, this.userId);
        }
        super.onSaveInstanceState(outState);
    }
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
            addFragment(R.id.fragmentContainer, new UserDetailsFragment());
        } else {
            this.userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule(this.userId))
                .build();
    }
    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
