package com.hiquanta.data.entity;

import com.google.gson.annotations.SerializedName;



/**
 * Created by hiquanta on 2016/9/26.
 */

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
