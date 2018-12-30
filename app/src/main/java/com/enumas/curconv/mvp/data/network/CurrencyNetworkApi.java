package com.enumas.curconv.mvp.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyNetworkApi {
    @GET("api/latest")
    Single<RateListResponse> getRatesListSingle(@Query("access_key") String accessKey);
}