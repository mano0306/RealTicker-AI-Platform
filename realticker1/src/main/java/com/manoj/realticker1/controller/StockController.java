package com.manoj.realticker1.controller;

import com.manoj.realticker1.model.Stock;
import com.manoj.realticker1.model.StockHistory;
import com.manoj.realticker1.service.AIService;
import com.manoj.realticker1.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private AIService aiService;

    @Autowired
    private StockService stockService;

    // 1. GET Top 10 Stocks for Dashboard
    @GetMapping("/top10")
    public List<Stock> getTop10() {
        return stockService.getTop10Stocks();
    }

    // 2. GET 6-Month Price History for Chart
    @GetMapping("/{ticker}/history")
    public StockHistory getHistory(@PathVariable String ticker) {
        Stock stock = stockService.getStockByTicker(ticker);
        List<Double> history = (stock != null) ? stock.getHistory() : Collections.emptyList();

        // Note: Inga empty analysis anupuroam, ஏன்னா namma separate POST endpoint vachirukkom.
        return new StockHistory(ticker, history, "Click analyze for AI insights");
    }

    // 3. POST Trigger AI Analysis (Phase 2 Requirement)
    @PostMapping("/{ticker}/analyze")
    public Map<String, String> triggerAnalysis(@PathVariable String ticker) {
        Stock stock = stockService.getStockByTicker(ticker);
        if (stock != null) {
            // Service-ah koopittu real LLM analysis-ah vaangi return pannum
            return aiService.analyzeStock(ticker, stock.getHistory());
        }
        return Collections.singletonMap("error", "Stock not found");
    }
}