package com.AssociativeArraysLambdaStreamAPI.Lab.Oddccurances;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        for (String word : words) {
            word = word.toLowerCase(Locale.ROOT);
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        System.out.println(map.entrySet().stream()
                .filter(e -> e.getValue() % 2 != 0)
                .map(Map.Entry::getKey)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "", ""))
        );
    }
}
