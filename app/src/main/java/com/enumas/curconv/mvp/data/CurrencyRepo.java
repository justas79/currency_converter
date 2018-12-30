package com.enumas.curconv.mvp.data;

import com.enumas.curconv.mvp.data.network.RateListResponse;
import io.reactivex.Single;

public interface CurrencyRepo {
    Single<RateListResponse> getRatesResp();
}