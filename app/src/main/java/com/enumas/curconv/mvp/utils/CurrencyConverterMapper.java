package com.enumas.curconv.mvp.utils;

import com.enumas.curconv.mvp.data.network.RateListResponse;
import com.enumas.curconv.mvp.ui.list.CurrencyListItemModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CurrencyConverterMapper {

    /**
     * Creates item list from response POJO for Currency Converter list adapter
     * @param r response with ratelist
     */
    public static List<CurrencyListItemModel> getListItems(RateListResponse r) {
        List<CurrencyListItemModel> result = new ArrayList<>();
        for (Map.Entry<String, String> responseEntry : r.getRates().entrySet()) {
            result.add(new CurrencyListItemModel(responseEntry.getValue(), responseEntry.getKey()));
        }
        return result;
    }
}
