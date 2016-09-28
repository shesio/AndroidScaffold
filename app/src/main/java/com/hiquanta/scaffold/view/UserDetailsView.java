package com.hiquanta.scaffold.view;


import com.hiquanta.scaffold.model.UserModel;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserDetailsView extends LoadDataView {
    void renderUser(UserModel user);
}
