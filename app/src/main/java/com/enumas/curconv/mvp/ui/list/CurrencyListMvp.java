package com.enumas.curconv.mvp.ui.list;

import com.enumas.curconv.mvp.data.network.RateListResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * MVP wrapper for currency list activity
 */
public interface CurrencyListMvp {
    interface View {
        void updateCurrencyList(List<CurrencyListItemModel> items);

        /**
         * starts circular progress
         */
        void startProgress();

        /**
         * stops progress
         */
        void stopProgress();

        /**
         * Shows base currency
         */
        void updateBaseInfo(String baseInfo);

        void logIssue(String msg);
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