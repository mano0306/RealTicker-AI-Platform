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

    // 1. GET Top 10 Stocks
    @GetMapping("/top10")
    public List<Stock> getTop10() {
        return stockService.getTop10Stocks();
    }

    // 2. GET Stock History
    @GetMapping("/{ticker}/history")
    public StockHistory getHistory(@PathVariable String ticker) {
        Stock stock = stockService.getStockByTicker(ticker);
        List<Double> history = (stock != null) ? stock.getHistory() : Collections.emptyList();
        return new StockHistory(ticker, history, "Click analyze for AI insights");
    }

    // 3. POST Analyze
    @PostMapping("/{ticker}/analyze")
    public Map<String, String> triggerAnalysis(@PathVariable String ticker) {
        Stock stock = stockService.getStockByTicker(ticker);
        if (stock != null) {
            return aiService.analyzeStock(ticker, stock.getHistory());
        }
        return Collections.singletonMap("error", "Stock not found");
    }

    // --- CRUD OPERATIONS ADDED BELOW ---

    // 4. CREATE - Add new stock
    @PostMapping("/add")
    public Map<String, String> addStock(@RequestBody Stock newStock) {
        stockService.addStock(newStock);
        return Collections.singletonMap("message", "Stock " + newStock.getTicker() + " added successfully!");
    }

    // 5. UPDATE - Update existing stock price
    @PutMapping("/{ticker}")
    public Map<String, String> updateStock(@PathVariable String ticker, @RequestBody Stock updatedData) {
        boolean success = stockService.updateStock(ticker, updatedData);
        if (success) {
            return Collections.singletonMap("message", "Stock " + ticker + " updated successfully!");
        }
        return Collections.singletonMap("error", "Stock not found");
    }

    // 6. DELETE - Remove a stock
    @DeleteMapping("/{ticker}")
    public Map<String, String> deleteStock(@PathVariable String ticker) {
        boolean success = stockService.deleteStock(ticker);
        if (success) {
            return Collections.singletonMap("message", "Stock " + ticker + " deleted successfully!");
        }
        return Collections.singletonMap("error", "Stock not found");
    }
}