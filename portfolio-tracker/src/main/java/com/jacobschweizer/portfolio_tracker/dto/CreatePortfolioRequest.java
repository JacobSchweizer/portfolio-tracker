package com.jacobschweizer.portfolio_tracker.dto;

public class CreatePortfolioRequest {

    public String name;

    // Required for JSON deserialization (Jackson)
    public CreatePortfolioRequest() {}

    public CreatePortfolioRequest(String name) {
        this.name = name;
    }
}