package com.kish.stocktrades.controller;

import com.kish.stocktrades.domain.TradeResponse;
import com.kish.stocktrades.exception.RecordNotFoundException;
import com.kish.stocktrades.model.StockTrade;
import com.kish.stocktrades.service.TradeService;
import com.kish.stocktrades.validator.TradeInputFeedMaxSizeConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@Validated
@RequestMapping(value = "/api/trade-service", produces = { MediaType.APPLICATION_JSON_VALUE })
public class StockTradeRestController {

    @Autowired
    private TradeService service;

    /**
     * Test Stock-Trade Service status is Healthy or not
     * @return String
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status() {
        return "Stock-Trade Service is up and healthy..."
                + LocalDate.now();
    }

    @RequestMapping(value = "/trades", method = RequestMethod.POST)
    public ResponseEntity<TradeResponse> postTrades(@RequestBody @NotEmpty(message = "No Statement(s) data found in request body.")
                                                    @TradeInputFeedMaxSizeConstraint List<@Valid StockTrade> tradeRequest)
                                                        throws RecordNotFoundException {
        return service.createOrUpdateTrade(tradeRequest);
    }

    @RequestMapping(value = "/trades", method = RequestMethod.GET)
    public ResponseEntity<TradeResponse> getTrades(@RequestParam(value="type", required=false) String type,
                                                   @RequestParam(value="userId", required=false) Integer userId)
                                                    throws RecordNotFoundException{
        return service.getTrades(type, userId);
    }

    @RequestMapping(value = "/trades/{id}", method = RequestMethod.GET)
    public ResponseEntity<TradeResponse> getTrades(@PathVariable(name = "id", required = false) Long id)
            throws RecordNotFoundException{
        System.out.println("getTrades = id "+id);
        return service.getTrades(id);
    }

    @DeleteMapping("/trades/{id}")
    public ResponseEntity<TradeResponse> deleteTrade(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new TradeResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), new ArrayList<StockTrade>()));
    }

    @PutMapping("/trades/{id}")
    public ResponseEntity<TradeResponse> updateTrade(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new TradeResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), new ArrayList<StockTrade>()));
    }

    @PatchMapping("/trades/{id}")
    public ResponseEntity<TradeResponse> patchTrade(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new TradeResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), new ArrayList<StockTrade>()));
    }
}