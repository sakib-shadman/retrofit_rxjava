package com.jontromanob.app.retrofit_rxjava.retrofit.login.call;

import com.jontromanob.app.retrofit_rxjava.retrofit.ApiClient;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.LoginApiInterface;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.model.LogInResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hi on 1/5/2018.
 */

public class CallLogIn {

    public static void call(String username, String password, String grant_type, Integer companyId,
                            String deviceId, String deviceType, String ipAddress, final LogInCallBack callback) {
        LoginApiInterface apiInterface = ApiClient.getClient ().create (LoginApiInterface.class);
        Call<LogInResponse> call = apiInterface.postLoginInfo (username, password, grant_type, companyId
                , deviceId, deviceType, ipAddress);
        call.enqueue (new Callback<LogInResponse> () {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if (response.body () != null) {
                    callback.onLogInSuccess (response.body ());
                } else {

                    String errorDescription = "",errorType = "";
                    try {
                        JSONObject jObjError = new JSONObject (response.errorBody ().string ());
                        errorType = jObjError.getString ("error");
                        errorDescription = jObjError.getString ("error_description");

                    } catch (Exception e) {
                        callback.onLogInFailure (errorType,errorDescription);
                    }
                    callback.onLogInFailure (errorType,errorDescription);

                }


            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                callback.onLogInFailure ("","There is some error please try again later!!");
            }
        });
    }


    public interface LogInCallBack {
        void onLogInSuccess(LogInResponse logInResponse);

        void onLogInFailure(String errorType, String errorMsg);

        void onServerFailure();
    }
}
