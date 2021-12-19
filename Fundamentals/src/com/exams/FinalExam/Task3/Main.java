package com.exams.FinalExam.Task3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> map = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String[] data = input.split(" ");
            String name = data[1];
            switch (data[0]) {
                case "Enroll":
                    if (map.containsKey(name)) {
                        System.out.printf("%s is already enrolled.%n", name);
                    } else {
                        map.put(name, new ArrayList<>());
                    }
                    break;
                case "Learn":
                    String spell = data[2];
                    if (!map.containsKey(name)) {
                        System.out.printf("%s doesn't exist.%n", name);
                    } else {
                        if (map.get(name).contains(spell)) {
                            System.out.printf("%s has already learnt %s.%n", name, spell);
                        } else {
                            map.get(name).add(spell);
                        }
                    }
                    break;
                case "Unlearn":
                    String spellName = data[2];
                    if (!map.containsKey(name)) {
                        System.out.printf("%s doesn't exist.%n", name);
                    } else {
                        if (!map.get(name).contains(spellName)) {
                            System.out.printf("%s doesn't know %s.%n", name, spellName);
                        } else {
                            map.get(name).remove(spellName);
                        }
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Heroes:");
        map.entrySet().stream()
                .sorted((m1, m2) -> {
                    if (m1.getValue().size() == m2.getValue().size()) {
                        return m1.getKey().compareTo(m2.getKey());
                    }
                    return Integer.compare(m2.getValue().size(), m1.getValue().size());
                }).forEach(e -> System.out.printf("== %s: %s%n", e.getKey(), String.join(", ", e.getValue())));
    }
}
