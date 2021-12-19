package com.exams.exam4.secretchat;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        String input = scanner.nextLine();
        while (!input.equals("Reveal")) {
            String[] commands = input.split(":\\|:");
            String order = commands[0];
            switch (order) {
                case "InsertSpace":
                    int index = Integer.parseInt(commands[1]);
                    String firstPart = message.substring(0, index);
                    String secondPart = message.substring(index);
                    message = firstPart + " " + secondPart;
                    System.out.println(message);
                    break;
                case "Reverse":
                    String substring = commands[1];
                    String reverse = new StringBuilder(substring).reverse().toString();
                    if (message.contains(substring)) {
                        reverse = escape(reverse);
                        substring = escape(substring);
                        message = message.replaceFirst(substring, reverse);
                        System.out.println(message);
                    } else System.out.println("error");
                    break;
                case "ChangeAll":
                    String subStr = commands[1];
                    if (message.contains(subStr)) {
                        String replacement = commands[2];
                        subStr = escape(subStr);
                        replacement = escape(replacement);
                        message = message.replaceAll(subStr, replacement);
                        System.out.println(message);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("You have a new text message: %s", message);
    }

    private static String escape(String reverse) {
        Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
        return SPECIAL_REGEX_CHARS.matcher(reverse).replaceAll("\\\\$0");
    }
}
