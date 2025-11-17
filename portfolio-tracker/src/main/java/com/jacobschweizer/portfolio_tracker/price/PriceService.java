package com.jacobschweizer.portfolio_tracker.price;

import java.math.BigDecimal;

public interface PriceService {

    /**
     * Returns the current price for the given symbol.
     * Throws an exception if the price cannot be fetched.
     */
    BigDecimal getCurrentPrice(String symbol);
}
