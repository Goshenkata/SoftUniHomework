package com.TextProcessing.Exercise.CeaserCypher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringBuilder cypher = new StringBuilder();
        for (char character : input.toCharArray()) {
            character += 3;
            cypher.append(character);
        }
        System.out.println(cypher);
    }
}
