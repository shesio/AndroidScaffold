package com.hiquanta.data.exception;


import com.hiquanta.domain.exception.ErrorBundle;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class RepositoryErrorBundle implements ErrorBundle {
    private final Exception exception;

    public RepositoryErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != null) {
            message = this.exception.getMessage();
        }
        return message;
    }
}
