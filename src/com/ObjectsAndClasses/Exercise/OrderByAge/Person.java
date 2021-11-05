package com.ObjectsAndClasses.Exercise.OrderByAge;

public class Person {
    String name;
    String id;
    int age;

    public Person(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s with ID: %s is %d years old.",name,id,age);
    }

    public int getAge() {
        return age;
    }
}
