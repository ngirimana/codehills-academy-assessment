package com.codehills.car_management.cli;

import com.codehills.car_management.backend.utils.AppConstants;

import java.util.HashMap;
import java.util.Map;

public class CliArgs {

    public static Map<String, String> parseKeyValue(String[] args, int startIndex) {
        Map<String, String> map = new HashMap<>();
        for (int i = startIndex; i < args.length; i++) {
            String a = args[i];
            if (!a.startsWith("--")) continue;
            String key = a.substring(2);
            if (i + 1 >= args.length || args[i + 1].startsWith("--")) {
                throw new IllegalArgumentException(AppConstants.MISSING_VALUE + key);
            }
            map.put(key, args[i + 1]);
            i++;
        }
        return map;
    }

    public static String require(Map<String, String> map, String key) {
        String v = map.get(key);
        if (v == null || v.isBlank()) {
            throw new IllegalArgumentException(AppConstants.MISSING + key);
        }
        return v;
    }

    public static long requireLong(Map<String, String> map, String key) {
        try { return Long.parseLong(require(map, key)); }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(AppConstants.INVALID + key);
        }
    }

    public static int requireInt(Map<String, String> map, String key) {
        try { return Integer.parseInt(require(map, key)); }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(AppConstants.INVALID + key);
        }
    }

    public static double requireDouble(Map<String, String> map, String key) {
        try { return Double.parseDouble(require(map, key)); }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(AppConstants.INVALID + key);
        }
    }
}
