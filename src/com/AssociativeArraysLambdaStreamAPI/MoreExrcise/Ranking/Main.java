package com.AssociativeArraysLambdaStreamAPI.MoreExrcise.Ranking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> contests = new LinkedHashMap<>();
        Map<String, List<User>> submissions = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("end of contests")) {
            String[] split = input.split(":");
            contests.put(split[0], split[1]);
            submissions.put(split[0], new ArrayList<>());
        }
        input = scanner.nextLine();
        while (input.equals("end of contests")) {
            String[] split = input.split("=>");
            String contest = split[0];
            String password = split[1];
            String username = split[2];
            int points = Integer.parseInt(split[3]);
            if (contests.get(contest).equals(password)) {
            }
            input = scanner.nextLine();
        }
    }

    static class User {
        String username;
        String password;

        public String getUsername() {
            return username;
        }

        public User setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public User setPassword(String password) {
            this.password = password;
            return this;
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
