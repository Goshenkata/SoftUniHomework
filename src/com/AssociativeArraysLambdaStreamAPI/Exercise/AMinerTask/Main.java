package com.AssociativeArraysLambdaStreamAPI.Exercise.AMinerTask;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Integer> map = new LinkedHashMap<>();
        while (!input.equalsIgnoreCase("stop")) {
            String resource = input;
            int amount = Integer.parseInt(scanner.nextLine());
            if (map.containsKey(resource)) {
                map.put(resource, map.get(resource) + amount);
            } else map.put(resource, amount);
            input = scanner.nextLine();
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
