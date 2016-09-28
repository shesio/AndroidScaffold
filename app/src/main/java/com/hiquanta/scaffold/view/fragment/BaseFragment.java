package com.hiquanta.scaffold.view.fragment;

import android.app.Fragment;
import android.widget.Toast;

import com.hiquanta.scaffold.internal.di.HasComponent;

/**
 * Created by hiquanta on 2016/9/26.
 */

public abstract class BaseFragment extends Fragment{
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
