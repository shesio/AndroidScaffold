package com.hiquanta.scaffold.repository;

import com.hiquanta.scaffold.User;

import java.util.List;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserRepository {
    Observable<List<User>> users();
    Observable<User> user(final int userId);
}
