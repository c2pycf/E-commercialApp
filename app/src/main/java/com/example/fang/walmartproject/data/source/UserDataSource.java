package com.example.fang.walmartproject.data.source;

import com.example.fang.walmartproject.data.UserImformation;

public interface UserDataSource {
    void saveUser(UserImformation userImformation);

    UserImformation getUser();

    void updateUser(UserImformation userImformation);

    void signOut();

    void saveFname(String fname);

    void saveLname(String lname);

    void saveEmail(String email);

    void saveAddress(String address);

    void saveMobile(String mobile);

}
