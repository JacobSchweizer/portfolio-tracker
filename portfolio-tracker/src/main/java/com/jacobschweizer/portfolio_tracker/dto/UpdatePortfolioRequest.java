package com.jacobschweizer.portfolio_tracker.dto;

public class UpdatePortfolioRequest {

    public String name;

    public UpdatePortfolioRequest() {
        // required by Jackson
    }

    public UpdatePortfolioRequest(String name) {
        this.name = name;
    }
}