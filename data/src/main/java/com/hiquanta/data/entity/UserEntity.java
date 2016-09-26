package com.hiquanta.data.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hiquanta on 2016/9/26.
 */
@Getter@Setter
public class UserEntity {
    @SerializedName("id")
    private int userId;

    @SerializedName("cover_url")
    private String coverUrl;

    @SerializedName("full_name")
    private String fullname;

    @SerializedName("description")
    private String description;

    @SerializedName("followers")
    private int followers;

    @SerializedName("email")
    private String email;

    public UserEntity() {
        //empty
    }
    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.userId + "\n");
        stringBuilder.append("cover url=" + this.coverUrl + "\n");
        stringBuilder.append("fullname=" + this.fullname + "\n");
        stringBuilder.append("email=" + this.email + "\n");
        stringBuilder.append("description=" + this.description + "\n");
        stringBuilder.append("followers=" + this.followers + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
