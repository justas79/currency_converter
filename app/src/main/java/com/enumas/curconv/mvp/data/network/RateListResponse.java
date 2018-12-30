package com.enumas.curconv.mvp.data.network;

import java.util.Map;

/**
 * Model for currency rates response
 */
public class RateListResponse {
    private boolean success;
    private float timestamp;
    private String base;
    private String date;
    Map<String, String> rates;

    public boolean isSuccess() {
        return success;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Map<String, String> getRates() {
        return rates;
    }
}
