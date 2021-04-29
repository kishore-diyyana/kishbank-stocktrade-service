package com.kish.stocktrades.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kish.stocktrades.model.StockTrade;

import java.util.List;

public class TradeResponse {

    public TradeResponse (int statusCode, List<StockTrade> stockTrades) {
        this.statusCode = statusCode;
        this.stockTrades = stockTrades;
    }

    int statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<StockTrade> stockTrades;

    public List<StockTrade> getStockTrades() {
        return stockTrades;
    }

    public void setStockTrades(List<StockTrade> stockTrades) {
        this.stockTrades = stockTrades;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
