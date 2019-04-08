package com.example.contentarticle.network;

import com.example.contentarticle.model.retrovit.ResponseLogin;
import com.example.contentarticle.model.retrovit.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone
    );
}
