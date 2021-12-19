package com.AssociativeArraysLambdaStreamAPI.MoreExrcise.Ranking;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> contests = new LinkedHashMap<>();
        String input = scanner.nextLine();
        Map<String, Map<String, Integer>> submissions = new LinkedHashMap<>();
        while (!input.equals("end of contests")) {
            contests.put(input.split(":")[0], input.split(":")[1]);
            input = scanner.nextLine();
        }
        input = scanner.nextLine();
        while (!input.equals("end of submissions")) {
            String[] split = input.split("=>");
            String contest = split[0];
            String password = split[1];
            String username = split[2];
            int points = Integer.parseInt(split[3]);
            if (contests.containsKey(contest)) {
                if (contests.get(contest).equals(password)) {
                    if (submissions.containsKey(username)) {
                        if (submissions.get(username).containsKey(contest)) {
                            if (submissions.get(username).get(contest) < points) {
                                submissions.get(username).put(contest, points);
                            }
                        } else submissions.get(username).put(contest, points);
                    } else {
                        submissions.put(username, new LinkedHashMap<>());
                        submissions.get(username).put(contest, points);
                    }
                }
            }
            input = scanner.nextLine();
        }
        int maxPoints = Integer.MIN_VALUE;
        String maxPointStudent = "";
        for (Map.Entry<String, Map<String, Integer>> o : submissions.entrySet()) {
            int sum = o.getValue().values().stream().mapToInt(Integer::intValue).sum();
            if (maxPoints < sum) {
                maxPoints = sum;
                maxPointStudent = o.getKey();
            }
        }
        System.out.printf("Best candidate is %s with total %d points.%n", maxPointStudent, maxPoints);
        System.out.println("Ranking:");
        //At the end you have to print the info for the user with the most points in the format "Best candidate is {user} with total {total points} points.
        // ". After that print all students ordered by their names.
        // For each user print each contest with the points in descending order. See the examples.
        submissions.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(k -> {
                    System.out.println(k.getKey());
                    k.getValue().entrySet().stream()
                            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                            .forEach(kv -> System.out.printf("#  %s -> %d%n", kv.getKey(), kv.getValue()));
                });
    }
}