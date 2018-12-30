package com.enumas.curconv.mvp.di;

import com.enumas.curconv.mvp.data.CurrencyRepo;
import com.enumas.curconv.mvp.data.CurrencyRepoImpl;
import com.enumas.curconv.mvp.data.network.CurrencyNetworkApi;
import com.enumas.curconv.mvp.ui.list.CurrencyListActivityPresenter;
import com.enumas.curconv.mvp.ui.list.CurrencyListModel;
import com.enumas.curconv.mvp.ui.list.CurrencyListMvp;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrencyListModule {

    @Provides
    public CurrencyListMvp.Presenter provideCurrencyListActivityPresenter(CurrencyListMvp.Model model) {
        return new CurrencyListActivityPresenter(model);
    }

    @Provides
    public CurrencyListMvp.Model provideCurrencyListActivityModel(CurrencyRepo repo) {
        return new CurrencyListModel(repo);
    }

    /**
     * Repo will cache data from network call.
     */
    @Provides CurrencyRepo provideCurrencyRepo(CurrencyNetworkApi currencyNetworkApi) {
        return new CurrencyRepoImpl(currencyNetworkApi);
    }
}
