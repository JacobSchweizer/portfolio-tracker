package com.jacobschweizer.portfolio_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PositionResponse {

    private Long id;
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal avgBuyPrice;
    private LocalDate buyDate;
    private Long portfolioId;

    public PositionResponse(Long id,
                            String symbol,
                            BigDecimal quantity,
                            BigDecimal avgBuyPrice,
                            LocalDate buyDate,
                            Long portfolioId) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.avgBuyPrice = avgBuyPrice;
        this.buyDate = buyDate;
        this.portfolioId = portfolioId;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getAvgBuyPrice() {
        return avgBuyPrice;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }
}
