package com.manoj.realticker1.service;

import com.manoj.realticker1.model.Stock;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StockService {

    public List<Stock> getTop10Stocks() {
        List<Stock> stocks = new ArrayList<>();


        stocks.add(new Stock("AAPL", "Apple Inc", 185.40, 1.2, 78000000L, generateMockHistory(170, 190)));
        stocks.add(new Stock("MSFT", "Microsoft", 412.30, 0.8, 45000000L, generateMockHistory(390, 420)));
        stocks.add(new Stock("TSLA", "Tesla", 180.50, -2.1, 95000000L, generateMockHistory(160, 200)));
        stocks.add(new Stock("GOOGL", "Alphabet Inc", 150.20, 0.5, 30000000L, generateMockHistory(140, 160)));
        stocks.add(new Stock("AMZN", "Amazon", 175.10, 1.1, 40000000L, generateMockHistory(165, 185)));
        stocks.add(new Stock("META", "Meta Platforms", 485.60, -0.4, 25000000L, generateMockHistory(450, 500)));
        stocks.add(new Stock("NVDA", "NVIDIA Corp", 890.10, 3.5, 60000000L, generateMockHistory(800, 950)));
        stocks.add(new Stock("NFLX", "Netflix", 610.40, 1.4, 15000000L, generateMockHistory(580, 640)));
        stocks.add(new Stock("AMD", "AMD", 180.20, -1.5, 55000000L, generateMockHistory(160, 195)));
        stocks.add(new Stock("INTC", "Intel", 43.50, -0.2, 35000000L, generateMockHistory(38, 48)));

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
        return getTop10Stocks().stream()
                .filter(s -> s.getTicker().equalsIgnoreCase(ticker))
                .findFirst()
                .orElse(null);
    }
}