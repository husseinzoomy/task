package com.example.anagrams_checker_project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {

    // readability in the sorting method is better
    public static boolean areAnagramsUsingSorting(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() != text2.length()) {
            return false;
        }

        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }

    public static boolean areAnagramsUsingCounting(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() != text2.length()) {
            return false;
        }

        Map<Character, Integer> charFrequency = new HashMap<>();

        // Count character frequencies in text1
        for (char c : text1.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Compare character frequencies in text2
        for (char c : text2.toCharArray()) {
            if (!charFrequency.containsKey(c) || charFrequency.get(c) <= 0) {
                return false;
            }
            charFrequency.put(c, charFrequency.get(c) - 1);
        }

        return true;
    }

}