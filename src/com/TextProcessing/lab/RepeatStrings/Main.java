package com.TextProcessing.lab.RepeatStrings;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String text : input) {
            sb.append(repeatString(text, text.length()));
        }
        System.out.println(sb);
    }

    public static String repeatString(String text, int lenght) {
        StringBuilder reversed = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            reversed.append(text);
        }
        return String.valueOf(reversed);
    }
}
