package com.jacobschweizer.portfolio_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdatePositionRequest {

    public BigDecimal quantity;
    public BigDecimal avgBuyPrice;
    public LocalDate buyDate;

    public UpdatePositionRequest() {
        // needed by Jackson
    }

    public UpdatePositionRequest(BigDecimal quantity,
                                 BigDecimal avgBuyPrice,
                                 LocalDate buyDate) {
        this.quantity = quantity;
        this.avgBuyPrice = avgBuyPrice;
        this.buyDate = buyDate;
    }
}
