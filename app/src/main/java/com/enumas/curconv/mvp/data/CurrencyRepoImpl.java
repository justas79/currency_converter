package com.enumas.curconv.mvp.data;

import com.enumas.curconv.mvp.data.network.CurrencyNetworkApi;
import com.enumas.curconv.mvp.data.network.RateListResponse;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.enumas.curconv.mvp.di.ApiModule.ACCESS_KEY;

/**
 * @see com.enumas.curconv.mvp.data.CurrencyRepo
 */
public class CurrencyRepoImpl implements CurrencyRepo {

    private CurrencyNetworkApi currencyNetworkApi;

    @Inject
    public CurrencyRepoImpl(CurrencyNetworkApi currencyNetworkApi) {
        this.currencyNetworkApi = currencyNetworkApi;
    }

    @Override
    public Single<RateListResponse> getRatesResp() {
        return currencyNetworkApi.getRatesListSingle(ACCESS_KEY);
    }
}
