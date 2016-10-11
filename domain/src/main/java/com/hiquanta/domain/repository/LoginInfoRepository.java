package com.hiquanta.domain.repository;

import com.hiquanta.domain.LoginInfo;
import com.hiquanta.domain.User;


import rx.Observable;

/**
 * Created by hiquanta on 2016/10/10.
 */

public interface LoginInfoRepository {
    Observable<LoginInfo> LoginInfo(String userName, String passWord);
}
