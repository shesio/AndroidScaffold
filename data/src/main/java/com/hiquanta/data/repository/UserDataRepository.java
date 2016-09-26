package com.hiquanta.data.repository;

import com.hiquanta.scaffold.User;
import com.hiquanta.scaffold.repository.UserRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UserDataRepository implements UserRepository {
    @Override
    public Observable<List<User>> users() {
        return null;
    }

    @Override
    public Observable<User> user(int userId) {
        return null;
    }
}
