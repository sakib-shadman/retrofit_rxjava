package com.jontromanob.app.retrofit_rxjava.retrofit.login;



import com.jontromanob.app.retrofit_rxjava.retrofit.login.model.LogInResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Hi on 1/5/2018.
 */

public interface LoginApiInterface {

    @FormUrlEncoded
    @POST("token")
    Single<LogInResponse> postLoginInfo(

            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grant_type,
            @Field("companyId") Integer companyId,
            @Field("deviceId") String deviceID,
            @Field("deviceType") String deviceType,
            @Field("ipAddress") String ipAddress

    );
}
