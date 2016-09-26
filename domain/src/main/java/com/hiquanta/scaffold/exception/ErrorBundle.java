package com.hiquanta.scaffold.exception;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
