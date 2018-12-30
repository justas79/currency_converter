package com.enumas.curconv.mvp.ui.list;

public class CurrencyListItemModel {

    private String rate;
    private String code;

    public CurrencyListItemModel(String rate, String code) {
        this.rate = rate;
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
