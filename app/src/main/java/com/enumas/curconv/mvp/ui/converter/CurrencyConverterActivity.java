package com.enumas.curconv.mvp.ui.converter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.curencyconv.enumas.currencyconverter.R;
import com.enumas.curconv.mvp.di.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity where currency conversion to another currency is shown
 */
public class CurrencyConverterActivity extends AppCompatActivity implements CurrencyConverterMvp.View {

    @BindView(R.id.txt_view)
    TextView textView;

    @BindView(R.id.txt_fromthis_code)
    TextView textFromCode;

    @BindView(R.id.txt_to_code)
    TextView textToCode;

    @BindView(R.id.txt_withrate_value)
    TextView textRateValue;

    @BindView(R.id.txt_result_value)
    TextView textResultValue;

    @BindView(R.id.edit_amount)
    EditText editAmount;

    @BindView(R.id.btn_invert)
    Button btnInvert;

    @Inject
    CurrencyConverterMvp.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_exchange_layout);

        //Butterknife setup
        ButterKnife.bind(this);

        //Dagger injectiond
        ((App) getApplication()).getApplicationComponent().inject(this);

        //display "back" on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set view to presenter
        presenter.setView(this);

        //get initial values
        String baseCurrency = getIntent().getExtras().getString("base_currency");
        String selectedCurrency = getIntent().getExtras().getString("selected_currency");
        String selectedRate = getIntent().getExtras().getString("selected_rate");

        //initial amount
        int baseAmount = 100;

        presenter.displayMain(baseCurrency, selectedCurrency, selectedRate, baseAmount);

        //add listeners to controls
        btnInvert.setOnClickListener(v -> presenter.invertConversion());

        //listen to changes on amount. Update result immediately
        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.updateAmount(s.toString());
            }
        });

    }

    @Override
    public void displayMain(String baseCurrency, String selectedCurrency, String selectedRate, int baseAmount, String result) {
        editAmount.setText(String.valueOf(baseAmount));
        textFromCode.setText(baseCurrency);
        textToCode.setText(selectedCurrency);
        textRateValue.setText(selectedRate);
        textResultValue.setText(result);
    }

    @Override
    public String getBaseDisplay() {
        return textFromCode.getText().toString();
    }

    @Override
    public String getSelectedDisplay() {
        return textToCode.getText().toString();
    }

    @Override
    public Double getAmount() {
        return Double.parseDouble(editAmount.getText().toString());
    }

    @Override
    public void setBaseDisplay(String s) {
         textFromCode.setText(s);
    }

    @Override
    public void setSelectedDisplay(String s) {
        textToCode.setText(s);
    }

    @Override
    public void setRateDisplay(String s) {
        textRateValue.setText(s);
    }

    @Override
    public void setResult(String s) {
        textResultValue.setText(s);
    }

    @Override
    public void setAmount(String s) {
        editAmount.setText(s);
    }

    @Override
    public void logDebug(String s) {
        Log.e(CurrencyConverterActivity.class.getName(), s);
    }

    @Override
    public void logIssue(String s) {
        Log.e(CurrencyConverterActivity.class.getName(), s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT);
    }
}
