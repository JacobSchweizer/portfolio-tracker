package com.jacobschweizer.portfolio_tracker.repository;

import com.jacobschweizer.portfolio_tracker.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByName(String name);
}
