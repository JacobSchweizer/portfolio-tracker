package com.jacobschweizer.portfolio_tracker.price;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class AlphaVantagePriceService implements PriceService {

    private final WebClient webClient;
    private final String apiKey;
    private final String function;
    private final String baseUrl;

    public AlphaVantagePriceService(
            WebClient webClient,
            @Value("${alpha_vantage.api.key}") String apiKey,
            @Value("${alpha_vantage.api.function}") String function,
            @Value("${alpha_vantage.api.base-url}") String baseUrl
    ) {
        this.webClient = webClient;
        this.apiKey = apiKey;
        this.function = function;
        this.baseUrl = baseUrl;
    }

    @Override
    public BigDecimal getCurrentPrice(String symbol) {
        AlphaVantageGlobalQuoteResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(extractHost(baseUrl))      // e.g. www.alphavantage.co
                        .path("/query")
                        .queryParam("function", function) // GLOBAL_QUOTE
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(AlphaVantageGlobalQuoteResponse.class)
                .block(); // block is fine here in a simple app

        if (response == null || response.getGlobalQuote() == null || response.getGlobalQuote().getPrice() == null) {
            throw new RuntimeException("Could not fetch price for symbol: " + symbol);
        }

        return response.getGlobalQuote().getPrice();
    }

    /**
     * Helper: baseUrl is like "https://www.alphavantage.co", but WebClient's uri builder
     * wants just the host when we also call .scheme("https").
     */
    private String extractHost(String url) {
        // quick and dirty: remove "https://" prefix if present
        if (url.startsWith("https://")) {
            return url.substring("https://".length());
        }
        if (url.startsWith("http://")) {
            return url.substring("http://".length());
        }
        return url;
    }
}
