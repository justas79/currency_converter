package com.enumas.curconv.mvp.utils;

import com.enumas.curconv.mvp.data.network.RateListResponse;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Currency;

/**
 * Utilities class, contains helper methods.
 */
public class Utils {

    /**
     * Returns full display text for currency in converter screen. Symbol, title, code
     */
    public static String getDisplayCurrencyFull(String code) {
        Currency currency = Currency.getInstance(code);
        String symbol = currency.getSymbol();
        String displayName = currency.getDisplayName();
        return String.format("%s %s %s", symbol, displayName, code);
    }

    /**
     * Parses string of currency rate into Double
     */
    public static Double parseRate(String selectedRate) {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        df.setDecimalFormatSymbols(symbols);
        try {
            return df.parse(selectedRate).doubleValue();
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Returns formatted string from double
     */
    public static String formatRate(Double rateD) {
        DecimalFormat format = new DecimalFormat("0.######");
        return format.format(rateD);
    }

    public static String getBaseInfo(RateListResponse r) {
        return getDisplayCurrencyFull(r.getBase());
    }
}