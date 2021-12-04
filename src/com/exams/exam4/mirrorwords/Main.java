package com.exams.exam4.mirrorwords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("([@#])(?<wordOne>[A-Za-z]{3,})\\1\\1(?<wordTwo>[A-Za-z]{3,})\\1");
        Matcher matcher = pattern.matcher(input);
        List<String> words = new ArrayList<>();
        List<String> mirrored = new ArrayList<>();
        while (matcher.find()) {
            String wordOne = matcher.group("wordOne");
            String wordTwo = matcher.group("wordTwo");
            String reversed = new StringBuilder(wordTwo).reverse().toString();
            words.add(wordOne);
            if (wordOne.equals(reversed)) {
                mirrored.add(wordOne + " <=> " + wordTwo);
            }
        }
        if (words.isEmpty()) {
            System.out.println("No word pairs found!");
            System.out.println("No mirror words!");
        } else {
            System.out.printf("%d word pairs found!%n", words.size());
            if (mirrored.isEmpty()) {
                System.out.println("No mirror words!");
            } else {
                System.out.println("The mirror words are:");
                System.out.println(String.join(", ", mirrored));
            }
        }
    }
}
