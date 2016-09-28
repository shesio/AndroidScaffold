package com.hiquanta.scaffold;



import com.hiquanta.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UIThread  implements PostExecutionThread {
    @Inject
    public UIThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
