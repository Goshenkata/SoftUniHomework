package com.AssociativeArraysLambdaStreamAPI.Exercise.CompanyUsers;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Set<String>> map = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String company = input.split(" -> ")[0];
            String employeeID = input.split(" -> ")[1];
            if (!map.containsKey(company)) {
                map.put(company, new LinkedHashSet<>());
            }
            if (!map.get(company).contains(employeeID)) {
                map.get(company).add(employeeID);
            }

            input = scanner.nextLine();
        }
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Set<String>>comparingByKey())
                .forEach(e -> {
                    System.out.println(e.getKey());
                    e.getValue().forEach(s -> System.out.println("-- " + s));
                });

    }


}
