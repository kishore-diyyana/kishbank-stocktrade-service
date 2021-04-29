package com.kish.stocktrades.validator;

import com.kish.stocktrades.model.StockTrade;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * If the shares value is out of accepted range [1, 100], or
 * he type value is invalid (i.e. not 'buy' or 'sell'),
 * the API must return error code 400
 */
@Component
public class TradeValidator {
    /**
     * This method is used to validate input parameters of trade-type and shares
     * @param trades
     * @return List of type StockTrade
     */
    public List<StockTrade> validateStockTrade(List<StockTrade> trades) {
        return trades.stream()
                .filter(trade -> (trade.getShares() == 0 || trade.getShares() > 100 ||
                        !Stream.of("Sell", "Buy").anyMatch(trade.getType()::equalsIgnoreCase)))
                .collect(Collectors.toList());
    }
}
