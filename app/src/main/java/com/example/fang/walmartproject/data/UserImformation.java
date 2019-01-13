package com.example.fang.walmartproject.data;

import com.google.gson.annotations.SerializedName;

public class UserImformation {
    @SerializedName("msg")
    String message;

    @SerializedName("id")
    String userId;

    @SerializedName("firstname")
    String firstName;

    @SerializedName("lastname")
    String lastName;

    @SerializedName("email")
    String email;
    @SerializedName("mobile")
    String mobile;
    @SerializedName("appapikey")
    String userAppApiKey;

    public UserImformation(String message, String userId, String firstName, String lastName, String email, String mobile, String userAppApiKey) {
        this.message = message;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.userAppApiKey = userAppApiKey;
    }

    public String getLoginMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUserAppApiKey() {
        return userAppApiKey;
    }
}
