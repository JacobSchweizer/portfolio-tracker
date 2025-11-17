package com.jacobschweizer.portfolio_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PositionSummaryResponse {

    private Long positionId;
    private Long portfolioId;
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal avgBuyPrice;

    private BigDecimal currentPrice;
    private BigDecimal totalInvested;
    private BigDecimal currentValue;
    private BigDecimal unrealizedPnl;
    private LocalDate buyDate;

    public PositionSummaryResponse(Long positionId,
                                   Long portfolioId,
                                   String symbol,
                                   BigDecimal quantity,
                                   BigDecimal avgBuyPrice,
                                   BigDecimal currentPrice,
                                   BigDecimal totalInvested,
                                   BigDecimal currentValue,
                                   BigDecimal unrealizedPnl,
                                   LocalDate buyDate) {
        this.positionId = positionId;
        this.portfolioId = portfolioId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.avgBuyPrice = avgBuyPrice;
        this.currentPrice = currentPrice;
        this.totalInvested = totalInvested;
        this.currentValue = currentValue;
        this.unrealizedPnl = unrealizedPnl;
        this.buyDate = buyDate;
    }

    public Long getPositionId() {
        return positionId;
    }

    public Long getPortfolioId() {
        return portfolioId;
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

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public BigDecimal getTotalInvested() {
        return totalInvested;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public BigDecimal getUnrealizedPnl() {
        return unrealizedPnl;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }
}
