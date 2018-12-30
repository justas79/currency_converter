package com.enumas.curconv.mvp.di;


import com.enumas.curconv.mvp.ui.converter.CurrencyConverterActivity;
import com.enumas.curconv.mvp.ui.list.CurrencyListActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class, CurrencyListModule.class, ApiModule.class, CurrencyConverterModule.class})
public interface ApplicationComponent {
    void inject(CurrencyListActivity target);
    void inject(CurrencyConverterActivity target);
}