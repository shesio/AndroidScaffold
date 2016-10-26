package com.hiquanta.domain.repository;

import com.hiquanta.domain.User;

import java.util.List;

import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by hiquanta on 2016/9/26.
 */

public interface UserRepository {
  Observable<Reply<List<User>>> users(boolean isEvict);
   // Observable<List<User>> users(boolean isEvict);
   // Observable<User> user(final int userId);
    Observable<Reply<User>> user(final int userId);
}
