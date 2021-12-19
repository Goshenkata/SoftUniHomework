package com.AssociativeArraysLambdaStreamAPI.Lab.CountRealNumbers;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> countRealNumMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        for (int n : numbers) {
            if (countRealNumMap.containsKey(n)) {
                countRealNumMap.put(n,countRealNumMap.get(n) + 1);
            } else countRealNumMap.put(n,1);
        }
        for (Entry<Integer, Integer> entry : countRealNumMap.entrySet()) {
            System.out.printf("%d -> %d%n",entry.getKey(),entry.getValue());
        }
    }
}
