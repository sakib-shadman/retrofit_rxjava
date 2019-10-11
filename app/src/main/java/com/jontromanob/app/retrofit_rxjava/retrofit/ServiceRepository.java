package com.jontromanob.app.retrofit_rxjava.retrofit;

import com.jontromanob.app.retrofit_rxjava.retrofit.leaveapplication.model.LeaveApplicationDetails;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.model.LogInResponse;
import com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.model.VisitApplicationDetails;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceRepository {


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


    @GET("api/VisitApi/GetAll")
    Single<Response<List<VisitApplicationDetails>>> getAllVisitApplicationList(

            @Header("Authorization") String authorization
    );


    @GET("api/LeaveApi/GetAll")
    Observable<List<LeaveApplicationDetails>> getAllLeaveApplicationList(
            @Header("Authorization") String authorization
    );

    @GET("api/VisitApi/GetAll")
    Observable<List<VisitApplicationDetails>> getAllVisitApplicationListObservable(

            @Header("Authorization") String authorization
    );


}
