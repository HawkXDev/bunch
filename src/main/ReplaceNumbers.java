package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Need to find and replace the numbers from map with the corresponding names.
 * Two solutions: via Matcher and via String.replaceAll().
 */
public class ReplaceNumbers {
    public static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        String input = "Это стоит 1 бакс, а вот это - 12.\nПеременная имеет имя file1.\n110 - это число.";
        String[] arr = input.split("\n");
        ArrayList<String> data = new ArrayList<>(Arrays.asList(arr));

        System.out.println(Solution.getFor(data));
        System.out.println(OptimalSolution.getFor(data));
    }

    public static class Solution {
        public static String getFor(ArrayList<String> data) {
            StringBuilder result = new StringBuilder();
            Pattern pattern = Pattern.compile("(?<=(\\p{P}|\\s))\\d{1,2}(?=(\\p{P}|\\s))");

            for (String line : data) {
                StringBuilder sb = new StringBuilder();
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    int number = Integer.parseInt(matcher.group());

                    if (number <= 12) {
                        String replacement = map.get(number);
                        matcher.appendReplacement(sb, replacement);
                    }
                }

                matcher.appendTail(sb);
                result.append(sb).append("\n");
            }

            return result.toString();
        }
    }

    public static class OptimalSolution {
        public static String getFor(ArrayList<String> data) {
            StringBuilder result = new StringBuilder();

            for (String line : data) {
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    line = line.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
                }
                result.append(line).append("\n");
            }

            return result.toString();
        }
    }
}

