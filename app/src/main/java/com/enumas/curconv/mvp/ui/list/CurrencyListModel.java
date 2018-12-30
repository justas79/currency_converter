package com.enumas.curconv.mvp.ui.list;

import com.enumas.curconv.mvp.data.CurrencyRepo;
import com.enumas.curconv.mvp.data.network.RateListResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class CurrencyListModel implements CurrencyListMvp.Model {

    CurrencyRepo currencyRepo;

    @Inject
    public CurrencyListModel(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    @Override
    public Single<RateListResponse> getRates() {
        return currencyRepo.getRatesResp();
    }
}