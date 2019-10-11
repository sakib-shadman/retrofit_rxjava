package com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication;


import com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.model.VisitApplicationDetails;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ankon on 4/5/2018.
 */

public interface VisitApplicationInterface {


    @Headers({
            "Content-Type: application/json"
    })

    @GET("api/VisitApi/GetAll")

    Single<List<VisitApplicationDetails>> getAllVisitApplicationListSingle(
            @Header("Authorization") String authorization
    );


    @GET("api/VisitApi/GetAll")
    Single<Response<List<VisitApplicationDetails>>> getAllVisitApplicationList(

            @Header("Authorization") String authorization
    );

}
