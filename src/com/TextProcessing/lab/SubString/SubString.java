package com.TextProcessing.lab.SubString;

import java.util.Scanner;

public class SubString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String toRemove = scanner.nextLine();
        String text = scanner.nextLine();
        while (text.contains(toRemove)) {
            int beginIndex = text.indexOf(toRemove);
            int endIndex = beginIndex + toRemove.length();
            text = text.replace(text.substring(beginIndex, endIndex), "");
        }
        System.out.println(text);
    }
}
