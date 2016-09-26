package com.hiquanta.scaffold.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Getter@Setter
public class UserModel {
    private final int userId;

    public UserModel(int userId) {
        this.userId = userId;
    }

    private String coverUrl;
    private String fullName;
    private String email;
    private String description;
    private int followers;

    @Override
    public String toString() {
        return super.toString();
    }
}
