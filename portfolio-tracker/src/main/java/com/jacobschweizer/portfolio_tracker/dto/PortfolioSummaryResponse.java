package com.jacobschweizer.portfolio_tracker.dto;

import java.math.BigDecimal;

public class PortfolioSummaryResponse {

    private Long portfolioId;
    private String portfolioName;
    private int numberOfPositions;
    private BigDecimal totalInvested;
    private BigDecimal currentValue;
    private BigDecimal profit;

    public PortfolioSummaryResponse(Long portfolioId,
                                    String portfolioName,
                                    int numberOfPositions,
                                    BigDecimal totalInvested,
                                    BigDecimal currentValue,
                                    BigDecimal profit) {
        this.portfolioId = portfolioId;
        this.portfolioName = portfolioName;
        this.numberOfPositions = numberOfPositions;
        this.totalInvested = totalInvested;
        this.currentValue = currentValue;
        this.profit = profit;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public int getNumberOfPositions() {
        return numberOfPositions;
    }

    public BigDecimal getTotalInvested() {
        return totalInvested;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
