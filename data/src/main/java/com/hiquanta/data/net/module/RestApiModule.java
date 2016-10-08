package com.hiquanta.data.net.module;


import com.hiquanta.data.BuildConfig;
import com.hiquanta.data.cache.UserCache;
import com.hiquanta.data.net.RestApi;
import com.hiquanta.data.repository.datasource.CloudUserDataStore;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hiquanta on 2016/10/8.
 */
@Module
public class RestApiModule {
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

        return builder.build();
    }
    @Provides
    @Singleton
    public Retrofit provideRestAdapter( OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(RestApi.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }
    @Provides
    @Singleton
    public RestApi provideRestApi(Retrofit restAdapter) {
        return restAdapter.create(RestApi.class);
    }
//    @Provides
//    @Singleton
//    public UserManager provideUserManager(RestApi restApi) {
//        return new UserManager(restApi);
//    }
   @Provides
    @Singleton
    public CloudUserDataStore  provideCloudUserDataStore(UserCache userCache) {
        return new CloudUserDataStore(userCache);
    }
}
