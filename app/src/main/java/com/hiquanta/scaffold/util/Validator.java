package com.hiquanta.scaffold.util;

import android.text.TextUtils;


public class Validator {
    public static boolean vvalidateCredentials(String username, String password) {
        return (!TextUtils.isEmpty(username))&&(!TextUtils.isEmpty(password));
    }

}
