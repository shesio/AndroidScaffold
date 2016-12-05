package com.hiquanta.scaffold.view;

import android.content.Context;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface LoadDataView {
    void showLoading();
    void hideLoading();
    void showError(String message);
    Context context();
}
