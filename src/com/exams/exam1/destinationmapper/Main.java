package com.exams.exam1.destinationmapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String locations = scanner.nextLine();
        Pattern pattern = Pattern.compile("([=\\/])(?<destination>[A-Z][A-Za-z]{2,})\\1");
        Matcher matcher = pattern.matcher(locations);
        int travelPoints = 0;
        List<String> destinations = new ArrayList<>();
        while (matcher.find()) {
            String destination = matcher.group("destination");
            travelPoints += destination.length();
            destinations.add(destination);
        }
        System.out.printf("Destinations: %s%n", String.join(", ", destinations));
        System.out.printf("Travel Points: %d", travelPoints);
    }

}
