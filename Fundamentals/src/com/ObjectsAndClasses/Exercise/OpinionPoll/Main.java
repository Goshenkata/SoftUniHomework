package com.ObjectsAndClasses.Exercise.OpinionPoll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= Integer.parseInt(scanner.nextLine());
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split(" ");
            people.add(new Person(command[0], Integer.parseInt(command[1])));
        }
        people.removeIf(person -> person.getAge()<=30);
        people.stream().sorted(Comparator.comparing(Person::getName)).forEach(System.out::println);

    }

}
