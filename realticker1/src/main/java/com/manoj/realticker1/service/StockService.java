package com.manoj.realticker1.service;

import com.manoj.realticker1.model.Stock;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StockService {

    // Global list - Idhula thaan data save aagum
    private final List<Stock> stocks = new ArrayList<>();

    public StockService() {
        // Volume-ah quotes kulla "String"-ah kudukanum, ஏன்னா Stock.java-la adhu String-ah irukku
        stocks.add(new Stock("AAPL", "Apple Inc", 185.40, 1.2, "78M", generateMockHistory(170, 190)));
        stocks.add(new Stock("MSFT", "Microsoft", 412.30, 0.8, "45M", generateMockHistory(390, 420)));
        stocks.add(new Stock("TSLA", "Tesla", 180.50, -2.1, "95M", generateMockHistory(160, 200)));
        stocks.add(new Stock("GOOGL", "Alphabet Inc", 150.20, 0.5, "30M", generateMockHistory(140, 160)));
        stocks.add(new Stock("AMZN", "Amazon", 175.10, 1.1, "40M", generateMockHistory(165, 185)));
        stocks.add(new Stock("META", "Meta Platforms", 485.60, -0.4, "25M", generateMockHistory(450, 500)));
        stocks.add(new Stock("NVDA", "NVIDIA Corp", 890.10, 3.5, "60M", generateMockHistory(800, 950)));
        stocks.add(new Stock("NFLX", "Netflix", 610.40, 1.4, "15M", generateMockHistory(580, 640)));
        stocks.add(new Stock("AMD", "AMD", 180.20, -1.5, "55M", generateMockHistory(160, 195)));
        stocks.add(new Stock("INTC", "Intel", 43.50, -0.2, "35M", generateMockHistory(38, 48)));
    }

    public List<Stock> getTop10Stocks() {
        return stocks;
    }

    private List<Double> generateMockHistory(double min, double max) {
        List<Double> history = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 180; i++) {
            history.add(min + (max - min) * r.nextDouble());
        }
        return history;
    }

    public Stock getStockByTicker(String ticker) {
        return stocks.stream()
                .filter(s -> s.getTicker().equalsIgnoreCase(ticker))
                .findFirst()
                .orElse(null);
    }

    // --- CRUD Logic ---

    public void addStock(Stock stock) {
        if(stock.getHistory() == null || stock.getHistory().isEmpty()) {
            stock.setHistory(generateMockHistory(stock.getPrice() - 10, stock.getPrice() + 10));
        }
        stocks.add(stock);
    }

    public boolean updateStock(String ticker, Stock updatedData) {
        Stock s = getStockByTicker(ticker);
        if (s != null) {
            s.setPrice(updatedData.getPrice());
            s.setVolume(updatedData.getVolume());
            return true;
        }
        return false;
    }

    public boolean deleteStock(String ticker) {
        return stocks.removeIf(s -> s.getTicker().equalsIgnoreCase(ticker));
    }
}