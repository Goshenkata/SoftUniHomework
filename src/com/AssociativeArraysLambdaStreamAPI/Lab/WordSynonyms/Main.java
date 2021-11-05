package com.AssociativeArraysLambdaStreamAPI.Lab.WordSynonyms;

import java.util.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> map = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            String synonym = scanner.nextLine();
            map.putIfAbsent(word, new ArrayList<>());
            map.get(word).add(synonym);
        }
        for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().
                    toString()
                    .replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .strip());
        }
    }
}
