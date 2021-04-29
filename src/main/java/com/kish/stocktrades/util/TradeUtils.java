package com.kish.stocktrades.util;

import com.kish.stocktrades.model.StockTrade;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This class is used to build util methods.
 * @author Kishore Diyyana
 */
public final class TradeUtils {

    /**
     * This method is used to sort Trade list by id
     * @param trades
     * @return List
     */
    public static List<StockTrade> sortById(final List<StockTrade> trades) {
        //Sort based on ID
        List<StockTrade> sortedTrades = trades.stream().sorted(
                Comparator.comparing(StockTrade::getId)).collect(toList());
        return sortedTrades;
    }

}
