package com.ObjectsAndClasses.Exercise.Students;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] info = scanner.nextLine().split(" ");
            studentList.add(new Student(info[0], info[1], Double.parseDouble(info[2])));
        }
        studentList.stream().sorted(Comparator.comparing(Student::getGrade).reversed()).forEach(System.out::println);
    }
}
