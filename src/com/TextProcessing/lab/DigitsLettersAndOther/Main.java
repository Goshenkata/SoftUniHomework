package com.TextProcessing.lab.DigitsLettersAndOther;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringBuilder digits = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        StringBuilder special = new StringBuilder();
        for (char character : input.toCharArray()) {
            if (Character.isDigit(character)) {
                digits.append(character);
            } else if (Character.isAlphabetic(character)) {
                letters.append(character);
            } else {
                special.append(character);
            }
        }
        System.out.println(digits);
        System.out.println(letters);
        System.out.println(special);
    }
}
