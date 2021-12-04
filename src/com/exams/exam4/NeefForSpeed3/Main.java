package com.exams.exam4.NeefForSpeed3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> carMileage = new LinkedHashMap<>();
        Map<String, Integer> carFuel = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] orders = scanner.nextLine().split("\\|");
            String car = orders[0];
            int mileage = Integer.parseInt(orders[1]);
            int fuel = Integer.parseInt(orders[2]);
            carMileage.put(car, mileage);
            carFuel.put(car, fuel);
        }
        String input = scanner.nextLine();
        while (!input.equals("Stop")) {
            String[] command = input.split(" : ");
            String order = command[0];
            String car = command[1];
            switch (order) {
                case "Drive":
                    int distance = Integer.parseInt(command[2]);
                    int fuel = Integer.parseInt(command[3]);
                    if (carFuel.get(car) < fuel) {
                        System.out.println("Not enough fuel to make that ride");
                    } else {
                        carFuel.put(car, carFuel.get(car) - fuel);
                        carMileage.put(car, carMileage.get(car) + distance);
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", car, distance, fuel);
                        if (carMileage.get(car) >= 100000) {
                            System.out.printf("Time to sell the %s!%n", car);
                            carMileage.remove(car);
                            carFuel.remove(car);
                        }
                    }
                    break;
                case "Refuel":
                    int fueled = Integer.parseInt(command[2]);
                    if (carFuel.get(car) + fueled > 75) {
                        fueled = 75 - carFuel.get(car);
                    }
                    carFuel.put(car, carFuel.get(car) + fueled);
                    System.out.printf("%s refueled with %d liters%n", car, fueled);
                    break;
                case "Revert":
                    int km = Integer.parseInt(command[2]);
                    carMileage.put(car, carMileage.get(car) - km);
                    if (carMileage.get(car) < 10000) {
                        carMileage.put(car, 10000);
                    } else {
                        System.out.printf("%s mileage decreased by %d kilometers%n", car, km);
                    }
            }
            input = scanner.nextLine();
        }
        carMileage.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.<String, Integer>comparingByKey()))
                .forEach(e -> System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n", e.getKey(), e.getValue(), carFuel.get(e.getKey())));
    }
}