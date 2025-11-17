package com.jacobschweizer.portfolio_tracker.service;
import com.jacobschweizer.portfolio_tracker.model.Portfolio;
import com.jacobschweizer.portfolio_tracker.model.Position;
import com.jacobschweizer.portfolio_tracker.repository.PortfolioRepository;
import com.jacobschweizer.portfolio_tracker.repository.PositionRepository;
import org.springframework.stereotype.Service;
import com.jacobschweizer.portfolio_tracker.exception.PortfolioNotFoundException;
import com.jacobschweizer.portfolio_tracker.exception.PositionNotFoundException;
import com.jacobschweizer.portfolio_tracker.dto.PortfolioSummaryResponse;



import java.util.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PositionRepository positionRepository;  

    public PortfolioService(PortfolioRepository portfolioRepository,
                            PositionRepository positionRepository) {
        this.portfolioRepository = portfolioRepository;
        this.positionRepository = positionRepository;
    }

    // --- Portfolio methods ---

    public Portfolio createPortfolio(String name){
        Portfolio portfolio = new Portfolio(name);
        return portfolioRepository.save(portfolio);
    }

    public List<Portfolio> getAllPortfolios(){
        return portfolioRepository.findAll();
    }

    public Portfolio getPortfolioById(Long id){
        return portfolioRepository.findById(id)
            .orElseThrow(() -> new PortfolioNotFoundException(id));
    }

    public void deletePortfolio(Long id){
        portfolioRepository.deleteById(id);
    }

    public Portfolio updatePortfolioName(Long id, String newName) {
        Portfolio portfolio = portfolioRepository.findById(id)
            .orElseThrow(() -> new PortfolioNotFoundException(id));

        portfolio.setName(newName);
        return portfolioRepository.save(portfolio);
    }

    public PortfolioSummaryResponse getPortfolioSummary(Long portfolioId) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioNotFoundException(portfolioId));

        List<Position> positions = positionRepository.findByPortfolioId(portfolioId);

        int numberOfPositions = positions.size();

        // totalInvested = sum(quantity * avgBuyPrice)
        BigDecimal totalInvested = positions.stream()
                .map(p -> p.getQuantity().multiply(p.getAvgBuyPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new PortfolioSummaryResponse(
                portfolio.getId(),
                portfolio.getName(),
                numberOfPositions,
                totalInvested);
    }


    // --- Position methods ---

    public List<Position> getPositionsForPortfolio(Long portfolioId) {
        portfolioRepository.findById(portfolioId)
            .orElseThrow(() -> new PortfolioNotFoundException(portfolioId));

        return positionRepository.findByPortfolioId(portfolioId);
    }

   public Position addPositionToPortfolio(
        Long portfolioId,
        String symbol,
        BigDecimal quantity,
        BigDecimal avgBuyPrice,
        LocalDate buyDate) {

        Portfolio portfolio = portfolioRepository.findById(portfolioId)
            .orElseThrow(() -> new PortfolioNotFoundException(portfolioId));

        Position position = new Position(symbol, quantity, avgBuyPrice, buyDate);
        position.setPortfolio(portfolio);

        return positionRepository.save(position);
    }

   public void deletePosition(Long positionId) {
        if (!positionRepository.existsById(positionId)) {
            throw new PositionNotFoundException(positionId);
        }
        positionRepository.deleteById(positionId);
  }

    public Position updatePosition(Long positionId,
                               BigDecimal quantity,
                               BigDecimal avgBuyPrice,
                               LocalDate buyDate) {

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new PositionNotFoundException(positionId));

        position.setQuantity(quantity);
        position.setAvgBuyPrice(avgBuyPrice);
        position.setBuyDate(buyDate);

        return positionRepository.save(position);
    }
    
    public Position getPositionById(Long id) {
    return positionRepository.findById(id)
            .orElseThrow(() -> new PositionNotFoundException(id));
    }


    
}
