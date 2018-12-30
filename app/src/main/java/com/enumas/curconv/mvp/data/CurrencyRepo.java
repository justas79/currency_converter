package com.enumas.curconv.mvp.data;

import com.enumas.curconv.mvp.data.network.RateListResponse;
import io.reactivex.Single;

/**
 * Repository to get rates from network.
 * Possible enhancements : implement cache, add offline support etc.
 */
public interface CurrencyRepo {
    Single<RateListResponse> getRatesResp();
}