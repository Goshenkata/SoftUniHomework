package com.exams.exam3.TheImatataionGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String encrypted = scanner.nextLine();
        String command = scanner.nextLine();
        while (!command.equals("Decode")) {
            String order = command.split("\\|")[0];
            switch (order) {
                case "Move":
                    int numLetter = Integer.parseInt(command.split("\\|")[1]);
                    String second = encrypted.substring(0, numLetter);
                    String first = encrypted.substring(numLetter);
                    encrypted = first + second;
                    break;
                case "Insert":
                    int index = Integer.parseInt(command.split("\\|")[1]);
                    String value = command.split("\\|")[2];
                    String first2 = encrypted.substring(0, index);
                    String second2 = encrypted.substring(index);
                    encrypted = first2 + value + second2;
                case "ChangeAll":
                    String substring = command.split("\\|")[1];
                    String replacement = command.split("\\|")[2];
                    encrypted = encrypted.replaceAll(substring, replacement);
            }
            command = scanner.nextLine();
        }
        System.out.printf("The decrypted message is: %s", encrypted);
    }
}
