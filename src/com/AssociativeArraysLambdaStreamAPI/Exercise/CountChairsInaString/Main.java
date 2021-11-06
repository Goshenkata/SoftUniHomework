package com.AssociativeArraysLambdaStreamAPI.Exercise.CountChairsInaString;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char chr : text.toCharArray()) {
            if (chr != ' ') {
                if (map.containsKey(chr)) {
                    map.put(chr, map.get(chr) + 1);
                } else map.put(chr, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
