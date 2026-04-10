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
    private double price;         // 'currentPrice'-ah 'price' nu maathirukkom (Getter: getPrice())
    private double dailyChange;
    private String volume;        // 'Long'-ah 'String' nu maathirukkom (Example: "78M")
    private List<Double> history;
}
