package com.AssociativeArraysLambdaStreamAPI.Exercise.SoftUniParking;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].equals("register")) {
                if (map.containsKey(command[1]))
                    System.out.printf("ERROR: already registered with plate number %s%n", command[2]);
                else {
                    map.put(command[1], command[2]);
                    System.out.printf("%s registered %s successfully%n", command[1], command[2]);
                }
            } else if (command[0].equals("unregister")) {
                if (map.containsKey(command[1])) {
                    System.out.printf("%s unregistered successfully%n", command[1]);
                    map.remove(command[1]);
                } else System.out.printf("ERROR: user %s not found%n", command[1]);
            }
        }
        map.forEach((key, value) -> System.out.printf("%s => %s%n", key, value));
    }
}
