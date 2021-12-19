package com.TextProcessing.Exercise.CharacterMultiplier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        String input = scanner.nextLine();
        String biggerString;
        String smallerString;
        if (input.split(" ")[0].length() > input.split(" ")[1].length()) {
            biggerString = input.split(" ")[0];
            smallerString = input.split(" ")[1];
        } else {
            smallerString = input.split(" ")[0];
            biggerString = input.split(" ")[1];
        }
        for (int i = 0; i < smallerString.length(); i++) {
            sum += (smallerString.charAt(i) * biggerString.charAt(i));
        }
        for (int i = smallerString.length(); i < biggerString.length(); i++) {
            sum += biggerString.charAt(i);
        }
        System.out.println(sum);
    }
}
