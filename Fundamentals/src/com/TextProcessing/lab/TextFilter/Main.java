package com.TextProcessing.lab.TextFilter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] toRemoveArray = scanner.nextLine().split(", ");
        String text = scanner.nextLine();
        for (String toRemove : toRemoveArray) {
            while (text.contains(toRemove)) {
                int beginIndex = text.indexOf(toRemove);
                int endIndex = beginIndex + toRemove.length();
                text = text.replace(text.substring(beginIndex, endIndex), repeatString("*", toRemove.length()));
            }
        }
        System.out.println(text);
    }

    public static String repeatString(String text, int lenght) {
        StringBuilder reversed = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            reversed.append(text);
        }
        return String.valueOf(reversed);
    }

}
