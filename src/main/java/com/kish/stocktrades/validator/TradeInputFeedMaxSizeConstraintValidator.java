package com.kish.stocktrades.validator;

import com.kish.stocktrades.model.StockTrade;
import com.kish.stocktrades.props.TradeProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is to validate input Statement list cannot contain
 * more than specified size defined application.properties file (inputFeedSize).
 *
 * @author Kishore Diyyana
 */
public class TradeInputFeedMaxSizeConstraintValidator implements ConstraintValidator<TradeInputFeedMaxSizeConstraint, List<StockTrade>> {

    @Autowired
    TradeProperties props;

    @Override
    public boolean isValid(List<StockTrade> statements, ConstraintValidatorContext constraintValidatorContext) {
        return statements.size() <= props.getInputFeedSize();
    }
}