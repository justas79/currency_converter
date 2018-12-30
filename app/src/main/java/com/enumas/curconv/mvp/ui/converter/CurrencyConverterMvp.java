package com.enumas.curconv.mvp.ui.converter;

/**
 * MVP wrapper for currency converter activity
 */
public interface CurrencyConverterMvp {

    interface View {
        void displayMain(String baseCurrency, String selectedCurrency, String selectedRate, int baseAmount, String result);

        String getBaseDisplay();
        String getSelectedDisplay();
        Double getAmount();

        void setBaseDisplay(String s);
        void setSelectedDisplay(String s);
        void setRateDisplay(String s);
        void setResult(String s);
        void setAmount(String s);

        void logIssue(String s);
        void logDebug(String s);
    }

    interface Presenter {
        void setView(CurrencyConverterMvp.View view);
        void displayMain(String baseCurrency, String selectedCurrency, String selectedRate, int baseAmount);
        void invertConversion();
        void updateAmount(String s);
    }

    interface Model {
        String getSelectedRate();
        void setSelectedRate(String rate);
    }
}