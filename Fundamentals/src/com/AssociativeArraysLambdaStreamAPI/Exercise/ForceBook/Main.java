package com.AssociativeArraysLambdaStreamAPI.Exercise.ForceBook;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> map = new TreeMap<>();
        String input = scanner.nextLine();
        while (!input.equals("Lumpawaroo")) {
            String forceUser;
            String forceSide;
            if (input.contains(" | ")) {
                forceSide = input.split(" \\| ")[0];
                forceUser = input.split(" \\| ")[1];
                if (!map.containsKey(forceSide)) {
                    map.put(forceSide, new ArrayList<>());
                }
                if (!userExists(map, forceUser)) {
                    map.get(forceSide).add(forceUser);
                }
            } else {
                forceSide = input.split(" -> ")[1];
                forceUser = input.split(" -> ")[0];
                if (!map.containsKey(forceSide)) {
                    map.put(forceSide, new ArrayList<>());
                }
                if (userExists(map, forceUser)) {
                    map = changeSide(map, forceSide, forceUser);
                } else {
                    map.get(forceSide).add(forceUser);
                }
                System.out.printf("%s joins the %s side!%n", forceUser, forceSide);
            }
            input = scanner.nextLine();
        }
        map.entrySet().stream()
                .sorted(Comparator.comparingInt(l -> l.getValue().size()))
                .sorted(Map.Entry.comparingByKey())
                .filter(s -> !s.getValue().isEmpty())
                .forEach(ent -> {
                    System.out.printf("Side: %s, Members: %d%n", ent.getKey(), ent.getValue().size());
                    ent.getValue().stream()
                            .sorted()
                            .forEach(name -> System.out.println("! " + name));
                });
    }

    private static Map<String, List<String>> changeSide(Map<String, List<String>> map, String toForceSide, String forceUser) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String name : entry.getValue()) {
                if (name.equals(forceUser)) {
                    map.get(toForceSide).add(forceUser);
                    map.get(entry.getKey()).remove(forceUser);
                    return map;
                }
            }
        }
        throw new IllegalStateException();
    }

    private static boolean userExists(Map<String, List<String>> map, String forceUser) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String name : entry.getValue()) {
                if (name.equals(forceUser)) return true;
            }
        }
        return false;
    }
}
