package com.hiquanta.scaffold.view;

import com.hiquanta.scaffold.model.UserModel;

import java.util.Collection;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserListView extends LoadDataView ,RetryView,PullToRefresh{

    void renderUserList(Collection<UserModel> userModelCollection);

    void viewUser(UserModel userModel);
    void dataFrom(String from);
}
