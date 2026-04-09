package com.manoj.realticker1.model;

import java.util.List;

public class StockHistory {
    private String ticker;
    private List<Double> history;
    private String analysis; // 👈 Pudhu field add pannunga

    // Default Constructor
    public StockHistory() {}

    // Updated Constructor with 3 arguments
    public StockHistory(String ticker, List<Double> history, String analysis) {
        this.ticker = ticker;
        this.history = history;
        this.analysis = analysis;
    }

    // Getters and Setters
    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public List<Double> getHistory() { return history; }
    public void setHistory(List<Double> history) { this.history = history; }

    public String getAnalysis() { return analysis; }
    public void setAnalysis(String analysis) { this.analysis = analysis; }
}