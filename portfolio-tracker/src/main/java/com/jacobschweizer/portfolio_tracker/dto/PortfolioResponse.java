package com.jacobschweizer.portfolio_tracker.dto;

public class PortfolioResponse {

    private Long id;
    private String name;

    // Constructor used when building the DTO
    public PortfolioResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters so Jackson can serialize this to JSON
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
