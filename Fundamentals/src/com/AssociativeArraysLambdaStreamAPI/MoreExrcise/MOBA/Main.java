package com.AssociativeArraysLambdaStreamAPI.MoreExrcise.MOBA;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Map<String, Integer>> map = new LinkedHashMap<>();
        while (!input.equals("Season end")) {
            if (input.split(" -> ").length >= 2) {
                //Player Position skill
                String[] data = input.split(" -> ");
                String player = data[0];
                String position = data[1];
                int skill = Integer.parseInt(data[2]);
                if (!map.containsKey(player)) {
                    map.put(player, new LinkedHashMap<>());
                    map.get(player).put(position, skill);
                } else {
                    if (!map.get(player).containsKey(position)) {
                        map.get(player).put(position, skill);
                    } else {
                        if (map.get(player).get(position) < skill) {
                            map.get(player).put(position, skill);
                        }
                    }
                }
            } else {
                String playerOne = input.split(" vs ")[0];
                String playerTwo = input.split(" vs ")[1];
                boolean isCommonPositionFound = false;
                if (map.containsKey(playerTwo) && map.containsKey(playerOne)) {
                    for (Map.Entry<String, Integer> pl1Entry : map.get(playerOne).entrySet()) {
                        for (Map.Entry<String, Integer> pl2Entry : map.get(playerTwo).entrySet()) {
                            if (pl1Entry.getKey().equals(pl2Entry.getKey())) {
                                isCommonPositionFound = true;
                                break;
                            }
                        }
                        if (isCommonPositionFound) break;
                    }
                    if (isCommonPositionFound) {
                        int pl1Total = map.get(playerOne).values().stream().mapToInt(Integer::intValue).sum();
                        int pl2Total = map.get(playerTwo).values().stream().mapToInt(Integer::intValue).sum();
                        if (pl1Total > pl2Total) {
                            map.remove(playerTwo);
                        } else if (pl2Total > pl1Total) {
                            map.remove(playerOne);
                        }
                    }
                }
            }
            input = scanner.nextLine();
        }
        //sorting
        map.entrySet().stream().sorted((n1, n2) -> {
            int n1Sum = n1.getValue().values().stream().mapToInt(Integer::intValue).sum();
            int n2Sum = n2.getValue().values().stream().mapToInt(Integer::intValue).sum();
            if (n1Sum == n2Sum) {
                return n1.getKey().compareTo(n2.getKey());
            } else return Integer.compare(n2Sum, n1Sum);
        }).forEach(e -> {
            //{player}: {totalSkill} skill
            //- {position} <::> {skill}
            System.out.printf("%s: %d skill%n", e.getKey(), e.getValue().values().stream().mapToInt(Integer::intValue).sum());
            e.getValue().entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .forEach(p -> System.out.printf("- %s <::> %d%n", p.getKey(), p.getValue()));
        });
    }
}
