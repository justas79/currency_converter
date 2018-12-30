package com.enumas.curconv.mvp.ui.list;

import android.support.annotation.Nullable;
import android.util.Log;

import com.enumas.curconv.mvp.data.network.RateListResponse;
import com.enumas.curconv.mvp.utils.CurrencyConverterMapper;
import com.enumas.curconv.mvp.utils.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CurrencyListActivityPresenter implements CurrencyListMvp.Presenter {

    @Nullable
    private CurrencyListMvp.View view;
    private CurrencyListMvp.Model model;

    private Disposable subscription;


    public CurrencyListActivityPresenter(CurrencyListMvp.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CurrencyListMvp.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        view.startProgress();
        try {
            subscription = model.getRates()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<RateListResponse>() {
                        @Override
                        public void onSuccess(RateListResponse r) {
                            List<CurrencyListItemModel> items = CurrencyConverterMapper.getListItems(r);
                            view.updateCurrencyList(items);
                            view.updateBaseInfo(Utils.getBaseInfo(r));
                            view.stopProgress();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.logIssue("Could not fetch currencies from network. Sorry...");
                            view.stopProgress();
                        }
                    });
        } catch (Exception e) {
            view.stopProgress();
            view.logIssue("Could not fetch currencies from network. Sorry...");
        }
    }

    /**
     *  Make sure unsubscribe subscription from observable to avoid memory leaks etc.
     */
    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }
}
