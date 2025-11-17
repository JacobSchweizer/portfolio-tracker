package com.jacobschweizer.portfolio_tracker.repository;

import com.jacobschweizer.portfolio_tracker.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByPortfolioId(Long portfolioId);
}
