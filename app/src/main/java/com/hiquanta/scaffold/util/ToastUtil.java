package com.hiquanta.scaffold.util;

import android.widget.Toast;

import com.hiquanta.scaffold.AppContext;

/**
 * Created by hiquanta on 2016/10/9.
 */

public class ToastUtil {
    public static void showShort(String msg) {
        Toast.makeText(AppContext.get(), msg, Toast.LENGTH_SHORT).show();
    }
    public static void showLong(String msg) {
        Toast.makeText(AppContext.get(), msg, Toast.LENGTH_LONG).show();
    }
}
