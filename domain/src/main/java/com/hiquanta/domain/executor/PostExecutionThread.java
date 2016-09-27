package com.hiquanta.domain.executor;

import rx.Scheduler;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
