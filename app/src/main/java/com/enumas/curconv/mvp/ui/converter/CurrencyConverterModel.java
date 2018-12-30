package com.enumas.curconv.mvp.ui.converter;

public class CurrencyConverterModel implements CurrencyConverterMvp.Model {

    String rate;

    public CurrencyConverterModel() {
    }

    @Override
    public void setSelectedRate(String rate) {
        this.rate = rate;
    }

    public String getSelectedRate() {
        return rate;
    }
}
