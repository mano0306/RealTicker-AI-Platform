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

    // application.properties-la irundhu token-ah inga fetch pannum
    @Value("${huggingface.token}")
    private String token;

    private static final String HF_API_URL = "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2";

    public Map<String, String> analyzeStock(String ticker, List<Double> history) {
        try {
            String prompt = "Analyze this stock " + ticker + " with 6-month history: " + history.toString() + ". Is it Bullish or Bearish? Give a short 2-line summary.";

            String jsonInput = "{\"inputs\": \"" + prompt + "\"}";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HF_API_URL))
                    .header("Content-Type", "application/json")
                    // Inga namma variable 'token'-ah use pandrom
                    .header("Authorization", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, String> result = new HashMap<>();
            result.put("analysis", response.body());
            return result;

        } catch (Exception e) {
            return Collections.singletonMap("error", "AI Analysis failed: " + e.getMessage());
        }
    }
}
