package com.exams.FinalExam.Task2;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String password = scanner.nextLine();
            Pattern pattern = Pattern.compile("(.+)>(?<num>\\d{3})\\|(?<lower>[a-z]{3})\\|(?<upper>[A-Z]{3})\\|(?<all>[^><]{3})<\\1");
            Matcher matcher =  pattern.matcher(password);
            if (matcher.matches()){
                String encrypted = matcher.group("num")
                        + matcher.group("lower")
                        + matcher.group("upper")
                        + matcher.group("all");
                System.out.printf("Password: %s%n", encrypted);
            } else {
                System.out.println("Try another password!");
            }
        }
    }
}
