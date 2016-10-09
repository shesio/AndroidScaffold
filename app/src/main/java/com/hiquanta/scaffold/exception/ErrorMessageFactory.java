package com.hiquanta.scaffold.exception;

import android.content.Context;

import com.hiquanta.data.exception.NetworkConnectionException;
import com.hiquanta.data.exception.UserNotFoundException;
import com.hiquanta.scaffold.R;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class ErrorMessageFactory {
    private ErrorMessageFactory() {

    }
    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);
        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof UserNotFoundException) {
            message = context.getString(R.string.exception_message_user_not_found);
        }

        return message;
    }
//    private void disposeFailureInfo(Throwable e){
//        if(e.toString().contains("ConnectException")||e.toString().contains("UnknownHostException")){
//            System.out.println("net");
//        }
//    }
}
