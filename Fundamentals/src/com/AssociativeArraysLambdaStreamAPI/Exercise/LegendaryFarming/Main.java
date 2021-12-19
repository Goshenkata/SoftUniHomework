package com.AssociativeArraysLambdaStreamAPI.Exercise.LegendaryFarming;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        Map.Entry<String, Integer> winningEntry = null;
        while (winningEntry == null) {
            String[] input = scanner.nextLine().split(" ");
            for (int i = 0; i < input.length; i += 2) {
                String material = input[i + 1].toLowerCase(Locale.ROOT);
                int quantity = Integer.parseInt(input[i]);
                if (map.containsKey(material)) {
                    map.put(material, map.get(material) + quantity);
                } else map.put(material, quantity);
                if (obtainedItem(map) != null) break;
            }
            winningEntry = obtainedItem(map);
        }
        printWinningEntry(winningEntry.getKey());
        printAndRemoveKeyMaterials(map, winningEntry.getKey());
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf("%s: %d%n", e.getKey(), e.getValue()));
    }

    private static void printAndRemoveKeyMaterials(Map<String, Integer> map, String collectedMaterial) {
        map.put(collectedMaterial, map.get(collectedMaterial) - 250);
        if (!map.containsKey("motes")) map.put("motes", 0);
        if (!map.containsKey("shards")) map.put("shards", 0);
        if (!map.containsKey("fragments")) map.put("fragments", 0);
        List<Map.Entry<String, Integer>> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "motes":
                case "shards":
                case "fragments":
                    entries.add(entry);
                    break;
            }
        }
        entries.stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(e -> {
                    System.out.printf("%s: %d%n", e.getKey(), e.getValue());
                    map.remove(e.getKey());
                });
    }

    private static synchronized void printWinningEntry(String s) {
        if (s.equals("motes")) {
            System.out.println("Dragonwrath obtained!");
        } else if (s.equals("shards")) {
            System.out.println("Shadowmourne obtained!");
        } else if (s.equals("fragments")) {
            System.out.println("Valanyr obtained!");
        }
    }

    public static Map.Entry<String, Integer> obtainedItem(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 250 && ("shards".equals(entry.getKey()) || "fragments".equals(entry.getKey()) || "motes".equals(entry.getKey()))) {
                return entry;
            }
        }
        return null;
    }
}
