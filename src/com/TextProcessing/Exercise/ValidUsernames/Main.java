package com.TextProcessing.Exercise.ValidUsernames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] passwords = scanner.nextLine().split(", ");
        List<String> validPasswords = new ArrayList<>();
        for (String pass : passwords) {
            if (pass.length() >= 3 && pass.length() <= 16) {
                boolean isPassValid = true;
                for (char ch : pass.toCharArray()) {
                    if (!(Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '-' || ch == '_')) {
                        isPassValid = false;
                    }
                }
                if (isPassValid) validPasswords.add(pass);
            }
        }
        for (String valPass : validPasswords) {
            System.out.println(valPass);
        }
    }
}
