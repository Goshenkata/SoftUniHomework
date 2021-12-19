package com.exams.FinalExam.Task1;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String input = scanner.nextLine();
        while (!input.equals("Done")) {
            String[] data = input.split(" ");
            switch (data[0]) {
                case "Change":
                    String character = data[1];
                    String replacement = data[2];
                    text = text.replaceAll(character, replacement);
                    System.out.println(text);
                    break;
                case "Includes":
                    String subtring = data[1];
                    if (text.contains(subtring)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "End":
                    String substr = data[1];
                    if (text.endsWith(substr)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Uppercase":
                    text = text.toUpperCase(Locale.ROOT);
                    System.out.println(text);
                    break;
                case "FindIndex":
                    String chr = data[1];
                    System.out.println(text.indexOf(chr));
                    break;
                case "Cut":
                    int startIndex = Integer.parseInt(data[1]);
                    int count = Integer.parseInt(data[2]);
                    text = text.substring(startIndex, startIndex+count);
                    System.out.println(text);
            }
            input = scanner.nextLine();
        }
    }
}
