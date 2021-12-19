package com.exams.exam3.AdAstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("([#|])(?<item>[A-Za-z ]+)\\1(?<date>\\d{2}\\/\\d{2}\\/\\d{2})\\1(?<calories>\\d{1,5})\\1");
        Matcher matcher = pattern.matcher(input);
        List<Food> foodList = new ArrayList<>();
        while (matcher.find()){
            String item = matcher.group("item");
            String date = matcher.group("date");
            int calories = Integer.parseInt(matcher.group("calories"));
            foodList.add(new Food(item, date, calories));
        }
        if (foodList.isEmpty()){
            System.out.println("You have food to last you for: 0 days!");
        } else {
            int sum = foodList.stream().mapToInt(Food::getCalories).sum();
            System.out.printf("You have food to last you for: %d days!%n", sum/2000);
        }
        foodList.forEach(System.out::println);
    }
    static class Food {
        String name;
        String expirationDate;
        int calories;

        public Food(String name, String expirationDate, int calories) {
            this.name = name;
            this.expirationDate = expirationDate;
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public Food setName(String name) {
            this.name = name;
            return this;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public Food setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public int getCalories() {
            return calories;
        }

        public Food setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        @Override
        public String toString() {
            return String.format("Item: %s, Best before: %s, Nutrition: %d", name, expirationDate, calories);
        }
    }
}
