package com.hiquanta.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.hiquanta.data.entity.LoginInfoEntity;
import com.hiquanta.data.entity.UserEntity;
import com.hiquanta.data.exception.NetworkConnectionException;
import com.hiquanta.data.net.component.DaggerRestApiComponent;
import com.hiquanta.data.net.module.RestApiModule;

import java.util.List;

import javax.inject.Inject;

import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * http://www.jianshu.com/p/2b0aeb6b6b61
 * Created by hiquanta on 2016/10/9.
 */

public class RestApiWrapper {
    @Inject
    RestApi restApi;
    private final Context context;

    public RestApiWrapper(Context context) {
        this.context = context;
        initComponent();
    }

    private void initComponent() {
        DaggerRestApiComponent.builder().restApiModule(new RestApiModule()).build().inject(this);
    }


    public Observable<List<UserEntity>> userEntityList() {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                restApi.userEntityList()
                        .subscribe(userEntities -> subscriber.onNext(userEntities)
                                , throwable -> subscriber.onError(new NetworkConnectionException(throwable.getCause()))
                                , () -> subscriber.onCompleted()
                        );

            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }


    public Observable<UserEntity> userEntityById(int userId) {
        return restApi.userEntityById(userId);
    }

    public Observable<LoginInfoEntity> doLogin(int userId) {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                restApi.doLogin(userId)
                        .subscribe(userEntities -> subscriber.onNext(userEntities)
                                , throwable -> subscriber.onError(new NetworkConnectionException(throwable.getCause()))
                                , () -> subscriber.onCompleted()
                        );
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
