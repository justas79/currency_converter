package com.curencyconv.enumas.currencyconverter;

import com.enumas.curconv.mvp.data.network.RateListResponse;
import com.enumas.curconv.mvp.ui.list.CurrencyListActivityPresenter;
import com.enumas.curconv.mvp.ui.list.CurrencyListMvp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterTests {

    CurrencyListMvp.Model mockModel;
    CurrencyListMvp.View mockView;
    CurrencyListMvp.Presenter presenter;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setup() {

        mockModel = mock(CurrencyListMvp.Model.class);

        //mock get rates
        RateListResponse rateListResponse = mock(RateListResponse.class);
        Map<String, String> rates = new HashMap<>();
        rates.put("USD", "1.12");
        when(rateListResponse.getRates()).thenReturn(rates);
        when(rateListResponse.getBase()).thenReturn("EUR");
        when(mockModel.getRates()).thenReturn(Single.just(rateListResponse));

        //mock view
        mockView = mock(CurrencyListMvp.View.class);

        //presenter to test
        presenter = new CurrencyListActivityPresenter(mockModel);
        presenter.setView(mockView);
    }

    /**
     * Verify if view was invoked for: start, stop progress, update base, update list.
     * Verify no invocation of logIssue
     */
    @Test
    public void testViewInteractions() {
        presenter.loadData();
        verify(mockView, times(1)).startProgress();
        verify(mockView, times(1)).stopProgress();
        verify(mockView, times(1)).updateBaseInfo(any());
        verify(mockView, times(1)).updateCurrencyList(any());
        verify(mockView, times(0)).logIssue(any());
    }

}
