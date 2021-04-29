package com.kish.stocktrades.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This class is to validate input Trade list cannot contain
 * more than specified size defined application.properties file (app.inputfeedSize).
 *
 * @author Kishore Diyyana
 */
@Constraint(validatedBy = TradeInputFeedMaxSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TradeInputFeedMaxSizeConstraint {

    String message() default "The input Stock Trade list cannot contain more than configured size in app";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
