package com.ObjectsAndClasses.Exercise.OrderByAge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Person> people = new ArrayList<>();
        while (!input.equalsIgnoreCase("End")) {
            String[] commands = input.split(" ");
            people.add(new Person(commands[0], commands[1], Integer.parseInt(commands[2])));
            input = scanner.nextLine();
        }
        people.stream().sorted(Comparator.comparing(Person::getAge)).forEach(System.out::println);
    }
}
