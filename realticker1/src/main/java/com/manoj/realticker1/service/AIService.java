package com.manoj.realticker1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class AIService {

    @Value("${huggingface.token}")
    private String token;

    // Corrected URL
    private static final String HF_API_URL = "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.3";

    public Map<String, String> analyzeStock(String ticker, List<Double> history) {
        try {
            String prompt = "Analyze " + ticker + " stock based on history: " + history.toString() + ". Is it Bullish or Bearish? Give a 2-line summary.";
            String jsonInput = "{\"inputs\": \"" + prompt.replace("\"", "\\\"") + "\"}";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HF_API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token.trim())
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Console-la idhu print aagudhaa-nu paarunga (Black text)
            System.out.println("Hugging Face Status: " + response.statusCode());
            System.out.println("Hugging Face Body: " + response.body());

            Map<String, String> result = new HashMap<>();
            if (response.statusCode() == 200) {
                result.put("analysis", response.body());
            } else if (response.statusCode() == 503) {
                result.put("analysis", "AI model is warming up... wait 30s.");
            } else {
                result.put("analysis", "Error: " + response.statusCode());
            }
            return result;

        } catch (Exception e) {
            return Collections.singletonMap("error", "Failed: " + e.getMessage());
        }
    }
}