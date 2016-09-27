package com.hiquanta.domain.view;


import com.hiquanta.domain.model.UserModel;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserDetailsView extends LoadDataView {
    void renderUser(UserModel user);
}
