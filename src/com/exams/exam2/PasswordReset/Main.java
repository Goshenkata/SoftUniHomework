package com.exams.exam2.PasswordReset;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        String command = scanner.nextLine();
        while (!command.equals("Done")) {
            switch (command.split(" ")[0]) {
                case "TakeOdd":
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i <= password.length()-1; i++) {
                        if (!(i%2==0)) {
                           sb.append(password.charAt(i));
                        }
                    }
                    password = sb.toString();
                    System.out.println(password);
                    break;
                case "Cut":
                    int index = Integer.parseInt(command.split(" ")[1]);
                    int lenght = Integer.parseInt(command.split(" ")[2]);
                    String substr = password.substring(index, index+lenght);
                    password = password.replaceFirst(substr, "");
                    System.out.println(password);
                    break;
                case "Substitute":
                    String toReplace= command.split(" ")[1];
                    String substiture = command.split(" ")[2];
                    if (password.contains(toReplace)) {
                        password = password.replaceAll(toReplace, substiture);
                        System.out.println(password);
                    } else System.out.println("Nothing to replace!");
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.printf("Your password is: %s", password);
    }
}
