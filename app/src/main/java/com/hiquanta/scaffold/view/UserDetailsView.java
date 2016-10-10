package com.hiquanta.scaffold.view;


import com.hiquanta.scaffold.model.UserModel;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserDetailsView extends LoadDataView,RetryView {
    void renderUser(UserModel user);
}
