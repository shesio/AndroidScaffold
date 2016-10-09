package com.hiquanta.domain.interactor;

import rx.Subscriber;


/**
 * Created by hiquanta on 2016/9/26.
 */

public abstract class DefaultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
//    /**
//     * 错误回调
//     */
//    protected abstract void onError(A ex);
}
