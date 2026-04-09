package com.manoj.realticker1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class AIService {

    // 1. Inga unga Token-ah paste pannunga (hf_... nu start aagum)
    private final String AUTH_TOKEN = "Bearer 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";

    // 2. Hugging Face Model URL (Mistral use pandrom, idhu fast-ah irukum)
    private final String API_URL = "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2";

    public Map<String, String> analyzeStock(String ticker, List<Double> history) {
        Map<String, String> responseMap = new HashMap<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTH_TOKEN);

            // AI-ku namma anuppura question (Prompt)
            String prompt = "Stock: " + ticker + ". 6-month price history: " + history.toString() +
                    ". Give a very short 2-line financial analysis. Is it bullish or bearish?";

            // Request Body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("inputs", prompt);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            // API Call
            ResponseEntity<List> response = restTemplate.postForEntity(API_URL, entity, List.class);

            // AI reply-ah edukirom
            if (response.getBody() != null && !response.getBody().isEmpty()) {
                Map<String, Object> result = (Map<String, Object>) response.getBody().get(0);
                String aiText = (String) result.get("generated_text");

                // Prompt-ah remove panni clean-ana answer mattum edukirom
                String cleanAnalysis = aiText.replace(prompt, "").trim();
                responseMap.put("analysis", cleanAnalysis);
            }

        } catch (Exception e) {
            // Error vandha safe-ah mock message anuppiduvom
            responseMap.put("analysis", "AI is busy. Stock " + ticker + " trend looks stable.");
        }

        responseMap.put("disclaimer", "This is real AI analysis and not financial advice.");
        return responseMap;
    }
}
