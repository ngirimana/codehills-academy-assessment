package com.codehills.car_management.cli;
import com.codehills.car_management.backend.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public static List<String> tokenize(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        char quote = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"' || c == '\'') {
                if (!inQuotes) { inQuotes = true; quote = c; continue; }
                if (quote == c) { inQuotes = false; continue; }
            }

            if (!inQuotes && Character.isWhitespace(c)) {
                if (!cur.isEmpty()) {
                    out.add(cur.toString());
                    cur.setLength(0);
                }
            } else {
                cur.append(c);
            }
        }

        if (!cur.isEmpty()) out.add(cur.toString());
        if (inQuotes) throw new IllegalArgumentException(AppConstants.UNCLOSED_QUOTE);
        return out;
    }
}
