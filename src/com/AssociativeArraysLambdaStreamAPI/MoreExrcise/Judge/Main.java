package com.AssociativeArraysLambdaStreamAPI.MoreExrcise.Judge;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Map<String, Integer>> map = new LinkedHashMap<>();
        Map<String, Integer> studentsMax = new LinkedHashMap<>();
        while (!input.equals("no more time")) {
            String[] values = input.split(" -> ");
            String username = values[0];
            String contest = values[1];
            int points = Integer.parseInt(values[2]);
            if (map.containsKey(contest)) {
                if (map.get(contest).containsKey(username)) {
                    if (map.get(contest).get(username) < points) {
                        map.get(contest).put(username, points);
                        studentsMax.put(username, studentsMax.get(username) + (Math.abs(studentsMax.get(username) -points)));
                    }
                } else {
                    map.get(contest).put(username, points);
                    studentsMax = repl(studentsMax, username, points);
                }
            } else {
                map.put(contest, new LinkedHashMap<>());
                map.get(contest).put(username, points);
                studentsMax = repl(studentsMax, username, points);
            }
            input = scanner.nextLine();
        }
        map.entrySet().stream()
                .forEach(m -> {
                            System.out.printf("%s: %d participants%n", m.getKey(), m.getValue().size());
                            AtomicInteger count = new AtomicInteger(1);
                            m.getValue().entrySet()
                                    .stream()
                                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                                            .thenComparing(Map.Entry.comparingByKey()))
                                    .forEach(e -> {
                                        System.out.printf("%d. %s <::> %d%n", count.get(), e.getKey(), e.getValue());
                                        count.getAndIncrement();
                                    });
                        }
                );
        System.out.println("Individual standings:");
        AtomicInteger count = new AtomicInteger(1);
        studentsMax.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(o -> {
                    System.out.printf("%d. %s -> %d%n", count.get(), o.getKey(), o.getValue());
                    count.getAndIncrement();
                });
    }

    private static Map<String, Integer> repl(Map<String, Integer> studentsMax, String username, int points) {
        if (studentsMax.containsKey(username)) {
            studentsMax.put(username, studentsMax.get(username) + points);
        } else {
            studentsMax.put(username, points);
        }
        return studentsMax;
    }
}