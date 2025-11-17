package com.jacobschweizer.portfolio_tracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdatePositionRequest {

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    public BigDecimal quantity;

    @NotNull(message = "Average buy price is required")
    @PositiveOrZero(message = "Average buy price must be zero or positive")
    public BigDecimal avgBuyPrice;

    @NotNull(message = "Buy date is required")
    @PastOrPresent(message = "Buy date cannot be in the future")
    public LocalDate buyDate;

    public UpdatePositionRequest() {
    }

    public UpdatePositionRequest(BigDecimal quantity,
                                 BigDecimal avgBuyPrice,
                                 LocalDate buyDate) {
        this.quantity = quantity;
        this.avgBuyPrice = avgBuyPrice;
        this.buyDate = buyDate;
    }
}
