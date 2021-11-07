package com.AssociativeArraysLambdaStreamAPI.Exercise.Courses;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> map = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("end")) {
            String course = input.split(" : ")[0];
            String student = input.split(" : ")[1];
            List<String> studentList;
            if (!map.containsKey(course)) {
                map.put(course, new ArrayList<>());
            }
            map.get(course).add(student);
            input = scanner.nextLine();
        }
        map.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .forEach(e -> {
                    System.out.println(e.getKey() + ": " + e.getValue().size());
                    e.getValue().stream()
                            .sorted()
                            .forEach(s -> System.out.println("-- " + s));
                });
    }
}
