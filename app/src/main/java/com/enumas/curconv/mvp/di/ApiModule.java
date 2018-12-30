package com.enumas.curconv.mvp.di;

import com.enumas.curconv.mvp.data.network.CurrencyNetworkApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    //    //feddbcb0162c117e8a1503692aad99c9
    //    //http://data.fixer.io/api/latest?access_key=feddbcb0162c117e8a1503692aad99c9
    public static final String CURRENCY_URL = "http://data.fixer.io/api/latest?access_key=feddbcb0162c117e8a1503692aad99c9";
    public static final String CURRENCY_URL_BASE = "http://data.fixer.io/";
    public static final String ACCESS_KEY = "feddbcb0162c117e8a1503692aad99c9";

    @Provides
    public OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public CurrencyNetworkApi provideCurrencyApiService() {
        return provideRetrofit(CURRENCY_URL_BASE, provideClient()).create(CurrencyNetworkApi.class);
    }
}
