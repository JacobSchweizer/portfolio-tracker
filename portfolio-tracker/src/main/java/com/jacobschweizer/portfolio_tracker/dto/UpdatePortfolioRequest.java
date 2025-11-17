package com.jacobschweizer.portfolio_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePortfolioRequest {

    @NotBlank(message = "Portfolio name must not be empty")
    @Size(max = 100, message = "Portfolio name must be at most 100 characters")
    public String name;

    public UpdatePortfolioRequest() {}

    public UpdatePortfolioRequest(String name) {
        this.name = name;
    }
}