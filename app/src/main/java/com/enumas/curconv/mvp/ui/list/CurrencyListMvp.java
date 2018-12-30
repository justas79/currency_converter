package com.enumas.curconv.mvp.ui.list;

import com.enumas.curconv.mvp.data.model.Currency;
import com.enumas.curconv.mvp.data.network.RateListResponse;

import java.util.List;

import io.reactivex.Single;

public interface CurrencyListMvp {

    interface View {
        void updateCurrencyList(List<CurrencyListItemModel> items);
        void startProgress();
        void stopProgress();
        void setBaseCurrency(Currency currency);
        void displayUnableToFetchCurrencies();
        void updateBaseInfo(String baseInfo);
    }

    interface Presenter {
        void setView(CurrencyListMvp.View view);
        void loadData();
        void refreshCurrencyRatesAction();
        void showNetworkError();
        void rxUnsubscribe();
    }

    interface Model {
        Single<RateListResponse> getRates();
    }
}
