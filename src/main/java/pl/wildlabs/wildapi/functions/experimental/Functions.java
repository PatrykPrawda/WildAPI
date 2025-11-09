package pl.wildlabs.wildapi.functions.experimental;

import java.util.HashMap;
import java.util.Map;

public class Functions {

    public static String removeTabsAndSpaces(String text) {
        text = removeTabs(text);
        text = removeSpaces(text);

        return text;
    }

    public static String removeTabs(String text) {
        while (text.contains("\t")) {
            text = text.replace("\t", "");
        }

        return text;
    }

    public static String removeSpaces(String text) {
        while (text.contains(" ")) {
            text = text.replace(" ", "");
        }

        return text;
    }

    private static final int[] values = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    private static final String[] numerals = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    private static final Map<Character, Integer> romanToIntMap = new HashMap<>();

    static {
        romanToIntMap.put('I', 1);
        romanToIntMap.put('V', 5);
        romanToIntMap.put('X', 10);
        romanToIntMap.put('L', 50);
        romanToIntMap.put('C', 100);
        romanToIntMap.put('D', 500);
        romanToIntMap.put('M', 1000);
    }

    public static String toRoman(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        }

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                roman.append(numerals[i]);
            }
        }

        return roman.toString();
    }

    public static int fromRoman(String roman) {
        int total = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char ch = roman.charAt(i);
            int value = romanToIntMap.get(ch);

            if (value < prevValue) {
                total -= value;
            } else {
                total += value;
            }

            prevValue = value;
        }

        return total;
    }
}