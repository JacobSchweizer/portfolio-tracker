package com.jacobschweizer.portfolio_tracker.controller;
import jakarta.validation.Valid;


import com.jacobschweizer.portfolio_tracker.dto.CreatePositionRequest;
import com.jacobschweizer.portfolio_tracker.dto.PositionResponse;
import com.jacobschweizer.portfolio_tracker.model.Position;
import com.jacobschweizer.portfolio_tracker.service.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.jacobschweizer.portfolio_tracker.dto.UpdatePositionRequest;
import com.jacobschweizer.portfolio_tracker.exception.PositionNotFoundException;
import com.jacobschweizer.portfolio_tracker.dto.PositionSummaryResponse;


import java.util.List;

@RestController
@RequestMapping("/api/portfolios/{portfolioId}/positions")
public class PositionController {

    private final PortfolioService portfolioService;

    public PositionController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    private PositionResponse toResponse(Position position) {
        return new PositionResponse(
                position.getId(),
                position.getSymbol(),
                position.getQuantity(),
                position.getAvgBuyPrice(),
                position.getBuyDate(),
                position.getPortfolio().getId()
        );
    }

    // GET /api/portfolios/{portfolioId}/positions
    @GetMapping
    public List<PositionResponse> getPositionsForPortfolio(@PathVariable Long portfolioId) {
        List<Position> positions = portfolioService.getPositionsForPortfolio(portfolioId);
        return positions.stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{positionId}")
    public PositionResponse getPositionById(
        @PathVariable Long portfolioId,
        @PathVariable Long positionId) {
    Position position = portfolioService.getPositionById(positionId);
    if (!position.getPortfolio().getId().equals(portfolioId)) {
    throw new PositionNotFoundException(positionId); // or Forbidden
    }
    return toResponse(position);
    }

    // GET /api/portfolios/{portfolioId}/positions/{positionId}/summary
    @GetMapping("/{positionId}/summary")
    public PositionSummaryResponse getPositionSummary(
            @PathVariable Long portfolioId,
            @PathVariable Long positionId) {
        Position position = portfolioService.getPositionById(positionId);

            // Optional safety check: ensure this position belongs to the given portfolio
            if (!position.getPortfolio().getId().equals(portfolioId)) {
                // you could throw PositionNotFoundException here
                throw new PositionNotFoundException(positionId);
            }

            return portfolioService.getPositionSummary(positionId);
    }


    // POST /api/portfolios/{portfolioId}/positions
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PositionResponse addPositionToPortfolio(
            @PathVariable Long portfolioId,
            @RequestBody @Valid CreatePositionRequest request
    ) {
        Position created = portfolioService.addPositionToPortfolio(
                portfolioId,
                request.symbol,
                request.quantity,
                request.avgBuyPrice,
                request.buyDate
        );
        return toResponse(created);
    }

    @DeleteMapping("/{positionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePosition(
            @PathVariable Long portfolioId,
            @PathVariable Long positionId
    ) {
        // we ignore portfolioId for now, but it's part of the URL so must be present
        portfolioService.deletePosition(positionId);
    }

    // PUT /api/portfolios/{portfolioId}/positions/{positionId}
    @PutMapping("/{positionId}")
    public PositionResponse updatePosition(
        @PathVariable Long portfolioId,
        @PathVariable Long positionId,
        @RequestBody @Valid UpdatePositionRequest request) {

        Position updated = portfolioService.updatePosition(
            positionId,
            request.quantity,
            request.avgBuyPrice,
            request.buyDate);
        return toResponse(updated);
    }


    
}
