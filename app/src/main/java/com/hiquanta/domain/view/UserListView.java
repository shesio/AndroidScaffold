package com.hiquanta.domain.view;

import com.hiquanta.domain.model.UserModel;

import java.util.Collection;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserListView extends LoadDataView {

    void renderUserList(Collection<UserModel> userModelCollection);

    void viewUser(UserModel userModel);
}
