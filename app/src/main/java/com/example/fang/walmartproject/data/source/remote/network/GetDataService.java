package com.example.fang.walmartproject.data.source.remote.network;

import com.example.fang.walmartproject.data.UserImformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("shop_reg.php?")
    Call<String> getRegistrationResult(@Query("fname") String fname,
                                       @Query("lname") String lname,
                                       @Query("address") String address,
                                       @Query("email") String email,
                                       @Query("mobile") String mobile,
                                       @Query("password") String password);

    @GET("shop_login.php?")
    Call<List<UserImformation>> getLoginResult(@Query("mobile") String mobile,
                                               @Query("password") String password);

}
