package com.TextProcessing.Exercise.ReplaceRepeatingChars;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            while (i <= input.length() - 2 && input.charAt(i) == input.charAt(i + 1)) {
                i++;
            }
            output.append(input.charAt(i));
        }
        System.out.println(output);
    }
}
