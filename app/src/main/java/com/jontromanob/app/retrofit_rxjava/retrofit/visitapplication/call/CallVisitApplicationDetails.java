package com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.call;



import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ankon on 4/5/2018.
 */

public class CallVisitApplicationDetails {

  /*  public static void getAllVisitApplicationList(String authorization, final VisitApplicationDetailsCallback callback) {
        VisitApplicationInterface apiInterface = ApiClient.getClient().create(VisitApplicationInterface.class);
        Call<List<VisitApplicationDetails>> call = apiInterface.getAllVisitApplicationList(authorization);
        call.enqueue(new Callback<List<VisitApplicationDetails>> () {
            @Override
            public void onResponse(Call<List<VisitApplicationDetails>> call, Response<List<VisitApplicationDetails>> response) {
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        callback.onSuccess(response.body());
                    } else if (response.body().size() <= 0) {
                        callback.onNoList();
                    }
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<VisitApplicationDetails>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }


    public interface VisitApplicationDetailsCallback {
        void onSuccess(List<VisitApplicationDetails> visitApplicationDetailsList);

        void onFailure();

        void onNoList();
    }*/

}
