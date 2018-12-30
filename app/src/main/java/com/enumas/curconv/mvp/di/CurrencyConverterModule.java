package com.enumas.curconv.mvp.di;

import com.enumas.curconv.mvp.ui.converter.CurrencyConverterActivityPresenter;
import com.enumas.curconv.mvp.ui.converter.CurrencyConverterModel;
import com.enumas.curconv.mvp.ui.converter.CurrencyConverterMvp;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrencyConverterModule {

    @Provides
    public CurrencyConverterMvp.Presenter provideCurrencyConverterActivityPresenter(CurrencyConverterMvp.Model model) {
        return new CurrencyConverterActivityPresenter(model);
    }

    @Provides
    public CurrencyConverterMvp.Model provideCurrencyListActivityModel() {
        return new CurrencyConverterModel();
    }
}
