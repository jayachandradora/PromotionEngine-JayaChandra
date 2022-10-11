package com.org.promotion.util;

import java.util.HashMap;
import java.util.Map;

public class PriceList {
    private static Map<String, Double> prods = new HashMap<>();

    static {
        prods.put("A", 50.0);
        prods.put("B", 30.0);
        prods.put("C", 20.0);
        prods.put("D", 15.0);
    }

    public static Double getPrice(String productName) {
        return prods.get(productName);
    }
}
