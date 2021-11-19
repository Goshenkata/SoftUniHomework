package com.Regex.exercise.race;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> racers = List.of(scanner.nextLine().split(", "));
        Map<String, Integer> map = new LinkedHashMap<>();
        racers.forEach(r -> map.put(r, 0));
        String input = scanner.nextLine();
        while (!input.equals("end of race")) {
            StringBuilder builder = new StringBuilder();
            int distance = 0;
            for (char character : input.toCharArray()) {
                if (Character.isAlphabetic(character)) {
                    builder.append(character);
                } else if (Character.isDigit(character)) {
                    distance += Integer.parseInt(character + "");
                }
            }
            if (map.containsKey(builder.toString())) {
                map.put(builder.toString(), map.get(builder.toString()) + distance);
            }
            input = scanner.nextLine();
        }
        List<String> winners = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("1st place: " + winners.get(0));
        if (map.size() >= 2) System.out.println("2nd place: " + winners.get(1));
        if (map.size() >= 3) System.out.println("3rd place: " + winners.get(2));
    }

}
