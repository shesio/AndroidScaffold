package com.hiquanta.data.net;

import com.hiquanta.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface RestApi {
    String API_BASE_URL = "http://www.android10.org/myapi/";

    String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";

    String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntityById(final int userId);
}
