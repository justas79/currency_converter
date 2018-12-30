package com.enumas.curconv.mvp.ui.converter;

import android.support.annotation.Nullable;

import com.enumas.curconv.mvp.utils.Utils;


public class CurrencyConverterActivityPresenter implements CurrencyConverterMvp.Presenter {

    @Nullable
    CurrencyConverterMvp.View view;
    CurrencyConverterMvp.Model model;

    public CurrencyConverterActivityPresenter(CurrencyConverterMvp.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CurrencyConverterMvp.View view) {
        this.view = view;
    }

    @Override
    public void displayMain(String baseCode, String selectedCode, String selectedRate, int baseAmount) {

        //prepare display for base
        String baseStr = Utils.getDisplayCurrencyFull(baseCode);

        //prepare display for selected
        String selectedStr = Utils.getDisplayCurrencyFull(selectedCode);

        //calculate rate, make sure it's parsable
        Double rate = Utils.parseRate(selectedRate);

        //if could not parse rate, log/display error
        if (rate == null) {
            view.logIssue("Could not parse rate: " + selectedRate);
            return;
        }

        //calculate result
        Double result = baseAmount * rate;

        //update model to store current rate
        model.setSelectedRate(selectedRate);

        if (view != null) {
            view.displayMain(baseStr, selectedStr, selectedRate, baseAmount, Utils.formatRate(result));
        }
    }

    @Override
    public void invertConversion() {

        //get current values
        String selectedRate = model.getSelectedRate();
        String baseDisplay = view.getBaseDisplay();
        String selectedDisplay = view.getSelectedDisplay();
        Double amount = view.getAmount();
        Double invertedRateD = 1 / Utils.parseRate(selectedRate);

        //calculate new
        String invertedRate = Utils.formatRate(invertedRateD);
        String newResult = Utils.formatRate(amount * invertedRateD);

        //update model with new rate
        model.setSelectedRate(invertedRate);

        //update views
        view.setRateDisplay(invertedRate);
        view.setBaseDisplay(selectedDisplay);
        view.setSelectedDisplay(baseDisplay);
        view.setResult(newResult);

    }

    @Override
    public void updateAmount(String s) {
        try {
            Double amount = Double.parseDouble(s);
            Double rate = Utils.parseRate(model.getSelectedRate());
            view.setResult(Utils.formatRate(amount * rate));
        } catch (Exception e) {
            view.logDebug("could not calculate result on amount " + s);
            view.setResult("--");
        }
    }
}