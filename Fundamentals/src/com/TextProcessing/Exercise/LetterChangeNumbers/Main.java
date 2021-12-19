package com.TextProcessing.Exercise.LetterChangeNumbers;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String abc = scanner.nextLine();
        double totalSum = 0;
        while (!abc.equals("End")) {
            String[] inputs = abc.split("\\s+");
            for (String input : inputs) {
                char firstLetter = input.charAt(0);
                char lastLetter = input.charAt(input.length() - 1);
                input = input.replace(firstLetter + "", "");
                input = input.replace(lastLetter + "", "");
                int num = Integer.parseInt(input);
                double result;
                if (firstLetter >= 65 && firstLetter <= 90) {
                    result = num * 1.0 / (firstLetter - 64);
                } else {
                    result = num * (firstLetter - 96);
                }
                if (lastLetter >= 65 && lastLetter <= 90) {
                    result -= (lastLetter - 64);
                } else {
                    result += (lastLetter - 96);
                }
                totalSum += result;
            }
            abc = scanner.nextLine();
        }
        System.out.printf("%.2f", totalSum);
    }
}
