package com.manoj.realticker1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private String ticker;
    private String companyName;
    private Double currentPrice;
    private Double dailyChange;
    private Long volume;
    private List<Double> history;
}
