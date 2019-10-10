package com.jontromanob.app.retrofit_rxjava;

import com.jontromanob.app.retrofit_rxjava.retrofit.ServiceRepository;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.model.LogInResponse;
import com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.model.VisitApplicationDetails;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Hi on 10/10/2019.
 */

public class RepoRepository {

    private final ServiceRepository serviceRepository;

    public RepoRepository(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Single<Response<List<VisitApplicationDetails>>> getVisitApplications(String authorization) {
        return serviceRepository.getAllVisitApplicationList (authorization);
    }

    public Single<LogInResponse> getLoginInfo(String userName, String password,String grantType,Integer companyId) {
        return serviceRepository.postLoginInfo (userName,password,grantType,companyId,null,null,null);
    }
}
