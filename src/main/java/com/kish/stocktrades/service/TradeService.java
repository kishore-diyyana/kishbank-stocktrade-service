package com.kish.stocktrades.service;

import com.kish.stocktrades.domain.TradeResponse;
import com.kish.stocktrades.exception.RecordNotFoundException;
import com.kish.stocktrades.model.StockTrade;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * This interface serve and process business operations.
 *
 * @author Kishore Diyyana
 */
public interface TradeService {
    public ResponseEntity<TradeResponse> createOrUpdateTrade(List<StockTrade> trades) throws RecordNotFoundException;

    public ResponseEntity<TradeResponse> getTrades(String type, Integer userId) throws RecordNotFoundException;

    public ResponseEntity<TradeResponse> getTrades(Long id) throws RecordNotFoundException;

}
