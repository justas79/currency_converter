package com.enumas.curconv.mvp.ui.list;

import com.enumas.curconv.mvp.data.network.RateListResponse;

import java.util.List;

import io.reactivex.Single;

public interface CurrencyListMvp {

    interface View {
        void updateCurrencyList(List<CurrencyListItemModel> items);
        void startProgress();
        void stopProgress();
        void displayUnableToFetchCurrencies();
        void updateBaseInfo(String baseInfo);
    }

    interface Presenter {
        void setView(CurrencyListMvp.View view);
        void loadData();
        void rxUnsubscribe();
    }

    interface Model {
        Single<RateListResponse> getRates();
    }
}
