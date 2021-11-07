package com.AssociativeArraysLambdaStreamAPI.Exercise.StudentAcademy;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> map = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }
            map.get(name).add(grade);
        }
        map.entrySet().stream()
                .filter(e -> getAverage(e) >= 4.50)
                .sorted((e1, e2) -> Double.compare(getAverage(e2), getAverage(e1)))
                .forEach(s -> System.out.printf("%s -> %.2f%n", s.getKey(), getAverage(s)));
    }

    private static double getAverage(Map.Entry<String, List<Double>> e) {
        return e.getValue()
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);
    }
}
