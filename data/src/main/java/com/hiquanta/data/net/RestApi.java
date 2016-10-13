package com.hiquanta.data.net;

import com.hiquanta.data.entity.LoginInfoEntity;
import com.hiquanta.data.entity.UserEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hiquanta on 2016/10/8.
 */

public interface RestApi {
    String API_BASE_URL = "http://www.android10.org/myapi/";
    //  String API_BASE_URL = "http://192.168.1.201/myapi/";

    @GET("users.json")
    Observable<List<UserEntity>> userEntityList();

    @GET("user_{userId}.json")
    Observable<UserEntity> userEntityById(final @Path("userId") int userId);

    @GET("user_{userId}.json")
    Observable<LoginInfoEntity> doLogin(final @Path("userId") int userId);
}
