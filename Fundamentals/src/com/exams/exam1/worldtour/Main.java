package com.exams.exam1.worldtour;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stops = scanner.nextLine();
        String input = scanner.nextLine();
        while (!input.equals("Travel")) {
            String[] commands = input.split("[\\ :]");
            switch (commands[0]) {
                case "Add":
                    //adding
                    int index = Integer.parseInt(commands[2]);
                    String newStop = commands[3];
                    if (indexIsValid(index, stops)) {
                        String firstHalf = stops.substring(0, index);
                        String secondHalf = stops.substring(index);
                        stops = firstHalf + newStop + secondHalf;
                    }
                    break;
                case "Remove":
                    //removing
                    int startIndex = Integer.parseInt(commands[2]);
                    int endIndex = Integer.parseInt(commands[3]);
                    if (indexIsValid(startIndex, stops) && indexIsValid(endIndex, stops)) {
                        stops = stops.replaceFirst(stops.substring(startIndex, endIndex + 1), "");
                    }
                    break;
                case "Switch":
                    String oldString = commands[1];
                    String newString = commands[2];
                    stops = stops.replace(oldString, newString);
                    break;
            }
            System.out.println(stops);
            input = scanner.nextLine();
        }
        System.out.printf("Ready for world tour! Planned stops: %s", stops);
    }

    private static boolean indexIsValid(int index, String stops) {
        return index >= 0 && index <= stops.length() - 1;
    }
}
