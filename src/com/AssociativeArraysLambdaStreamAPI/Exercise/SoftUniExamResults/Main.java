package com.AssociativeArraysLambdaStreamAPI.Exercise.SoftUniExamResults;

import java.util.*;

/**
 * I'm losing my mind
 **/

public class Main {
    public static void main(String[] args) {
        //THE SCORE WILL BE SET TO -1 WHEN THE USER IS BANNED
        Map<String, Map<String, Integer>> mapOfSubmission = new HashMap<>();
        Map<String, Integer> langMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("exam finished")) {
            if (input.split("-").length == 3) {
                //adding a new user
                String language = input.split("-")[1];
                String username = input.split("-")[0];
                int points = Integer.parseInt(input.split("-")[2]);
                if (!mapOfSubmission.containsKey(language)) {
                    mapOfSubmission.put(language, new HashMap<>());
                    mapOfSubmission.get(language).put(username, points);
                    langMap.put(language, 1);
                } else {
                    //if the user has already submitted
                    if (mapOfSubmission.get(language).containsKey(username)) {
                        //overwrite if the user is not banned AND current points are higher than previous max
                        if (!(mapOfSubmission.get(language).get(username) == -1) && mapOfSubmission.get(language).get(username) <= points) {
                            mapOfSubmission.get(language).put(username, points);
                        }
                    } else {
                        //if he is not in, add him
                        mapOfSubmission.get(language).put(username, points);
                    }
                    langMap.put(language, langMap.get(language) + 1);
                }
            } else {
                //banned
                String username = input.split("-")[0];
                mapOfSubmission = banUser(mapOfSubmission, username);
            }
            input = scanner.nextLine();
        }

        System.out.println("Results: ");
        List<Submission> submissions = new ArrayList<>();
        mapOfSubmission.forEach((key, value) ->
                value.forEach((k, v) -> submissions.add(new Submission(k, v)))
        );
        submissions.stream()
                .sorted(Comparator.comparing(Submission::getScore).reversed()
                        .thenComparing(Submission::getUser))
                .filter(s -> s.getScore() != -1)
                .forEach(System.out::println);

        System.out.println("Submissions:");
        langMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(s -> System.out.println(s.getKey() + " - " + s.getValue()));

    }

    static class Submission {
        String user;
        int score;

        public Submission(String user, int score) {
            this.user = user;
            this.score = score;
        }

        public String getUser() {
            return user;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return user + " | " + score;
        }
    }

    private static Map<String, Map<String, Integer>> banUser(Map<String, Map<String, Integer>> map, String username) {
        //for every language
        for (Map.Entry<String, Map<String, Integer>> submission : map.entrySet()) {
            //for every entry
            for (Map.Entry<String, Integer> entry : submission.getValue().entrySet()) {
                //if the username matches, set the score to -1
                if (entry.getKey().equals(username)) {
                    map.get(submission.getKey()).put(username, -1);
                }
            }
        }
        return map;
    }
}
