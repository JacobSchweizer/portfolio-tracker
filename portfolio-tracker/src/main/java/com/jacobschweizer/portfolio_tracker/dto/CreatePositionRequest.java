package com.jacobschweizer.portfolio_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreatePositionRequest {

    public String symbol;
    public BigDecimal quantity;
    public BigDecimal avgBuyPrice;
    public LocalDate buyDate;

    public CreatePositionRequest() {
        // required by Jackson
    }
}

