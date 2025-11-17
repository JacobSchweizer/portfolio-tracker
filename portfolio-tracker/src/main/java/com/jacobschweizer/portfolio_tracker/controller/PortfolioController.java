package com.jacobschweizer.portfolio_tracker.controller;
import com.jacobschweizer.portfolio_tracker.dto.PortfolioResponse;
import com.jacobschweizer.portfolio_tracker.dto.CreatePortfolioRequest;
import com.jacobschweizer.portfolio_tracker.dto.UpdatePortfolioRequest;
import com.jacobschweizer.portfolio_tracker.dto.PortfolioSummaryResponse;
import jakarta.validation.Valid;



import com.jacobschweizer.portfolio_tracker.model.Portfolio;
import com.jacobschweizer.portfolio_tracker.service.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    private PortfolioResponse toResponse(Portfolio portfolio) {
    return new PortfolioResponse(
            portfolio.getId(),
            portfolio.getName()
    );
}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioResponse createPortfolio(@RequestBody @Valid CreatePortfolioRequest request) {
        Portfolio created = portfolioService.createPortfolio(request.name);
        return toResponse(created);
    }

    @GetMapping
    public List<PortfolioResponse> getAllPortfolios() {
     List<Portfolio> portfolios = portfolioService.getAllPortfolios();
     return portfolios.stream()
            .map(this::toResponse)
            .toList();
    }

    @GetMapping("/{id}")
    public PortfolioResponse getPortfolioById(@PathVariable Long id) {
        Portfolio portfolio = portfolioService.getPortfolioById(id);
        return toResponse(portfolio);
    }

    // GET /api/portfolios/{id}/summary
    @GetMapping("/{id}/summary")
        public PortfolioSummaryResponse getPortfolioSummary(@PathVariable Long id) {
        return portfolioService.getPortfolioSummary(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
    }

    // PUT /api/portfolios/{id}  -> update portfolio name
    @PutMapping("/{id}")
    public PortfolioResponse updatePortfolio(@PathVariable Long id, @RequestBody @Valid UpdatePortfolioRequest request) {
      Portfolio updated = portfolioService.updatePortfolioName(id, request.name);
      return toResponse(updated);
    }
}
