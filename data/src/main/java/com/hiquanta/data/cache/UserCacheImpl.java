package com.hiquanta.data.cache;

import android.content.Context;

import com.hiquanta.data.cache.serializer.JsonSerializer;
import com.hiquanta.scaffold.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UserCacheImpl {
    private static final String SETTINGS_FILE_NAME = "com.hiquanta.scaffold.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;
    @Inject
    public UserCacheImpl(Context context, JsonSerializer userCacheSerializer,
                         FileManager fileManager, ThreadExecutor executor) {
        if (context == null || userCacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = userCacheSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }
}
