package com.kish.stocktrades.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name="TBL_STOCKTRADE")
public class StockTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Please provide valid trade type")
    @Column(name="type")
    private String type;

    @NotNull(message = "Please provide valid userId")
    @Column(name="user_id")
    private Integer userId;

    @NotEmpty(message = "Please provide valid Trade Symbol")
    @EqualsAndHashCode.Include
    @Column(name="symbol")
    private String symbol;

    @NotNull(message = "Please provide valid shares")
    @Column(name="shares")
    private Integer shares;

    @NotNull(message = "Please provide valid Price")
    @Column(name="price")
    private BigDecimal price;

    @Column(name="time_stamp")
    private Long timestamp;

    public StockTrade(String type, Integer userId, String symbol,
                      Integer shares, BigDecimal price, Long timestamp) {
        this.type = type;
        this.userId = userId;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestamp = timestamp;
    }

    public StockTrade() { }

    public Long getId() {
        return id;
    }

    ////TODO - Set Id used to update existing record.
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
