package com.hiquanta.domain.view;

import android.content.Context;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface LoadDataView {
    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
    void showError(String message);

    Context context();
}
