package com.ObjectsAndClasses.Exercise.Students;

public class Student {
    private String firstName;
    private String lastNmae;
    private double grade;

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
