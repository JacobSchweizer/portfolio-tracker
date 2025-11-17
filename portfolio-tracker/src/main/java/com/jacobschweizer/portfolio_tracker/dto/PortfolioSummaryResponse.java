package com.jacobschweizer.portfolio_tracker.dto;

import java.math.BigDecimal;

public class PortfolioSummaryResponse {

    private Long portfolioId;
    private String portfolioName;
    private int numberOfPositions;
    private BigDecimal totalInvested;

    public PortfolioSummaryResponse(Long portfolioId,
                                    String portfolioName,
                                    int numberOfPositions,
                                    BigDecimal totalInvested) {
        this.portfolioId = portfolioId;
        this.portfolioName = portfolioName;
        this.numberOfPositions = numberOfPositions;
        this.totalInvested = totalInvested;
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
}
