package com.ObjectsAndClasses.Exercise.Students;

public class Student {
    private final String firstName;
    private final String lastNmae;
    private final double grade;

    public Student(String firstName, String lastNmae, double grade) {
        this.firstName = firstName;
        this.lastNmae = lastNmae;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%s %s: %.2f", firstName, lastNmae, grade);
    }

    public double getGrade() {
        return grade;
    }
}
