package com.enumas.curconv.mvp.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.curencyconv.enumas.currencyconverter.R;
import com.enumas.curconv.mvp.data.model.Currency;
import com.enumas.curconv.mvp.data.network.CurrencyNetworkApi;
import com.enumas.curconv.mvp.di.App;
import com.enumas.curconv.mvp.ui.converter.CurrencyConverterActivity;
import com.enumas.curconv.mvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity lists currencies in the list, shows conversion rate.
 * Selecting item from the list will take to another activity for single currency convert
 */
public class CurrencyListActivity extends AppCompatActivity implements CurrencyListMvp.View, CurrencyListAdapter.CurrencyListClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_circular)
    ProgressBar progressCircular;

    @BindView(R.id.txt_base_info)
    TextView textBaseInfo;

    @Inject
    CurrencyListMvp.Presenter presenter;

    @Inject
    CurrencyNetworkApi api;

    private CurrencyListAdapter listAdapter;
    private List<CurrencyListItemModel> currencyItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_list_layout);

        //Butterknife setup
        ButterKnife.bind(this);

        //Dagger injection
        ((App) getApplication()).getApplicationComponent().inject(this);

        //action bar
        setSupportActionBar(toolbar);

        //setup adapter for the currency list
        listAdapter = new CurrencyListAdapter(currencyItems, this);

        //recylcer view setup
        recyclerView.setAdapter(listAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Method derived from MVP View. Will update currency list
     */
    @Override
    public void updateCurrencyList(List<CurrencyListItemModel> items) {
        Log.e("--->", "updated currencly list from View method");
        listAdapter.setData(items);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void startProgress() {
        progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressCircular.setVisibility(View.GONE);
    }

    @Override
    public void setBaseCurrency(Currency currency) {
        Log.e(CurrencyListActivity.class.getName(), "base currency change not implemented");
    }

    @Override
    public void displayUnableToFetchCurrencies() {
    }

    @Override
    public void updateBaseInfo(String baseInfo) {
        textBaseInfo.setText("Base: " + baseInfo);
    }

    /**
     * Launch converter activity. Pass base currency and selected currency along with rates
     */
    @Override
    public void onItemClick(View v, int position) {
        Intent i = new Intent(v.getContext(), CurrencyConverterActivity.class);
        i.putExtra("base_currency", Constants.BASE_CURRENCY_CODE);
        i.putExtra("selected_currency", listAdapter.list.get(position).getCode());
        i.putExtra("selected_rate", listAdapter.list.get(position).getRate());
        v.getContext().startActivity(i);

    }
}
