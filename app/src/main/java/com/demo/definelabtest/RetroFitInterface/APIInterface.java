package com.demo.definelabtest.RetroFitInterface;

import com.demo.definelabtest.Constant.Urls;
import com.demo.definelabtest.Model.AllMatchesModelResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIInterface {

    @GET(Urls.SEARCH)
    Single<AllMatchesModelResponse> getAreaData();

}
