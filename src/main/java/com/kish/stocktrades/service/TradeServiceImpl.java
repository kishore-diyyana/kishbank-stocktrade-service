package com.kish.stocktrades.service;

import com.kish.stocktrades.domain.TradeResponse;
import com.kish.stocktrades.exception.RecordNotFoundException;
import com.kish.stocktrades.model.StockTrade;
import com.kish.stocktrades.repository.StockTradeRepository;
import com.kish.stocktrades.util.TradeUtils;
import com.kish.stocktrades.validator.TradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This class implements processing of business operations .
 *
 * @author Kishore Diyyana
 */
@Service("TradeService")
public class TradeServiceImpl implements TradeService {

	@Autowired
    TradeValidator validator;

	@Autowired
	StockTradeRepository repository;

	public ResponseEntity<TradeResponse> createOrUpdateTrade(List<StockTrade> trades)
														throws RecordNotFoundException {

		System.out.println("=========> "+trades);

		if (!CollectionUtils.isEmpty(trades)) {

			//When there are invalid trade-type and out of rage stocks, return 400 error
			if(!CollectionUtils.isEmpty(validator.validateStockTrade(trades))) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new TradeResponse(HttpStatus.BAD_REQUEST.value(), null));
			}
		}
		List<StockTrade> newTrades = new ArrayList<StockTrade>();
		trades.stream().forEach(trade -> {
			Optional<StockTrade> tradeDB = null;
				if (trade.getId() != null) {
					tradeDB = repository.findById(trade.getId());
				}
				//Update existing record if Id provide in request
				if(tradeDB != null && tradeDB.isPresent()) {
					StockTrade newEntity = tradeDB.get();
					newEntity.setType(trade.getType());
					newEntity.setSymbol(trade.getSymbol());
					newEntity.setShares(trade.getShares());
					newEntity.setUserId(trade.getUserId());
					newEntity.setPrice(trade.getPrice());
					newEntity.setTimestamp(trade.getTimestamp());
					newEntity = repository.save(newEntity);
					newTrades.add(newEntity);
				}
				//Create new record if id not provided in request
				else {
					trade = repository.save(trade);
					newTrades.add(trade);
				}
		});
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new TradeResponse(HttpStatus.CREATED.value(), newTrades));
	}

	public ResponseEntity<TradeResponse> getTrades(String type, Integer userId)
										throws RecordNotFoundException {
		List<StockTrade> trades = null;
		if (type == null && userId == null) {
			trades = this.getAllTrades();
		} else if (type != null && userId != null) {
			trades = this.getTradeByTypeAndUserId(type, userId);
		} else if (type != null) {
			trades = this.getTradeByType(type);
		} else if (userId != null) {
			trades = this.getTradeByUserId(userId);
		}
		if(!CollectionUtils.isEmpty(trades)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new TradeResponse(HttpStatus.OK.value(), trades));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new TradeResponse(HttpStatus.OK.value(), new ArrayList<StockTrade>()));
		}
	}

	public List<StockTrade> getAllTrades() throws RecordNotFoundException {
		//Sort based on ID
		List<StockTrade> sortedTrades = repository.findAll().stream().sorted(
				Comparator.comparing(StockTrade::getId)).collect(toList());
		if(!CollectionUtils.isEmpty(sortedTrades)) {
			return sortedTrades;
		} else {
			throw new RecordNotFoundException("No Trade record exists.");
		}
	}

	public List<StockTrade> getTradeByType(String type) throws RecordNotFoundException {

		List<StockTrade> sortedTrades = TradeUtils.sortById(repository.findByType(type));
		if(!CollectionUtils.isEmpty(sortedTrades)) {
			return sortedTrades;
		} else {
			throw new RecordNotFoundException("No Trade record exist for given Type: "+type);
		}
	}

	public List<StockTrade> getTradeByUserId(Integer userId) throws RecordNotFoundException {
		List<StockTrade> trades = repository.findByUserId(userId);
		List<StockTrade> sortedTrades = TradeUtils.sortById(trades);
		if(!CollectionUtils.isEmpty(sortedTrades)) {
			return sortedTrades;
		} else {
			throw new RecordNotFoundException("No Trade record exist for given userId: "+userId);
		}
	}

	public List<StockTrade> getTradeByTypeAndUserId(String type, Integer userId) throws RecordNotFoundException {
		List<StockTrade> sortedTrades = TradeUtils.sortById(repository.findByTypeAndUserId(type, userId));
		if(!CollectionUtils.isEmpty(sortedTrades)) {
			return sortedTrades;
		} else {
			throw new RecordNotFoundException(String.format("No Trade record exist for given type: %s and userId: %s ", type, userId));
		}
	}

	public ResponseEntity<TradeResponse> getTrades(Long id) throws RecordNotFoundException {

		List<StockTrade> trades = null;
		if (id != null) {
			Optional<StockTrade> tradeDB = repository.findById(id);
			if (tradeDB != null&& tradeDB.isPresent()) {
				StockTrade entity = tradeDB.get();
				trades = new ArrayList<StockTrade>();
				trades.add(entity);
			}
		}
		if(!CollectionUtils.isEmpty(trades)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new TradeResponse(HttpStatus.OK.value(), trades));
		} else {
			throw new RecordNotFoundException(String.format("No Trade record exist for given id: %s ", id));
		}
	}
}
