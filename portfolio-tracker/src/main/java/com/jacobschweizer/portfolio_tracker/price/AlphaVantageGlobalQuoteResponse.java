package com.jacobschweizer.portfolio_tracker.price;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AlphaVantageGlobalQuoteResponse {

    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    public GlobalQuote getGlobalQuote() {
        return globalQuote;
    }

    public static class GlobalQuote {

        @JsonProperty("01. symbol")
        private String symbol;

        @JsonProperty("05. price")
        private BigDecimal price;

        public String getSymbol() {
            return symbol;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }
}
