package com.AssociativeArraysLambdaStreamAPI.Lab.WordFilter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arrays.stream(scanner.nextLine().split(" "))
                .map(String::valueOf)
                .filter(s -> s.length() % 2 == 0)
                .forEach(System.out::println);
    }
}
