package com.jdtechsage.spendsenseai.utils;

import java.util.HashMap;
import java.util.Map;

public class ExpenseCategorizer {
    private static final Map<String, String> categoryKeywords = new HashMap<>();

    static {
        // Food related keywords
        categoryKeywords.put("food", "Food");
        categoryKeywords.put("restaurant", "Food");
        categoryKeywords.put("groceries", "Food");
        categoryKeywords.put("dinner", "Food");
        categoryKeywords.put("lunch", "Food");

        // Transportation
        categoryKeywords.put("gas", "Transportation");
        categoryKeywords.put("fuel", "Transportation");
        categoryKeywords.put("uber", "Transportation");
        categoryKeywords.put("taxi", "Transportation");

        // Shopping
        categoryKeywords.put("shopping", "Shopping");
        categoryKeywords.put("clothes", "Shopping");
        categoryKeywords.put("amazon", "Shopping");
    }

    public static String categorizeExpense(String description) {
        description = description.toLowerCase();

        for (Map.Entry<String, String> entry : categoryKeywords.entrySet()) {
            if (description.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        return "Other";
    }
}