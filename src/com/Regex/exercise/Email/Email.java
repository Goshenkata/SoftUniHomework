package com.Regex.exercise.Email;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Pattern patter = Pattern.compile("\\b[A-Za-z0-9][A-Za-z0-9.\\-._]*[A-Za-z0-9]+@[A-Za-z][A-Za-z-]*[A-Za-z](\\.[A-Za-z][A-Za-z-]*[A-Za-z])*\\b");
        Matcher matcher = patter.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
