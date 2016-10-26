package com.hiquanta.domain;




/**
 * Created by hiquanta on 2016/9/26.
 */

public class User {
    private  int userId;
    public User(){
        this.userId=-1;
    }
    public User(int userId) {
        this.userId = userId;
    }

    private String coverUrl;
    private String fullName;
    private String email;
    private String description;
    private int followers;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Details *****\n");
        stringBuilder.append("id=" + this.userId + "\n");
        stringBuilder.append("cover url=" + this.coverUrl + "\n");
        stringBuilder.append("fullname=" + this.fullName + "\n");
        stringBuilder.append("email=" + this.email + "\n");
        stringBuilder.append("description=" + this.description + "\n");
        stringBuilder.append("followers=" + this.followers + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
