package com.exams.exam1.plantDiscovery;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Plant> map = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split("<->");
            map.put(parts[0], new Plant(Integer.parseInt(parts[1]), new ArrayList<>()));
        }
        String input = scanner.nextLine();
        while (!input.equals("Exhibition")) {
            String[] parts = input.split(": | - ");
            String plant = parts[1];
            switch (parts[0]) {
                case "Rate":
                    int newRating = Integer.parseInt(parts[2]);
                    if (map.containsKey(plant)) {
                        map.put(plant, map.get(plant).addRating(newRating));
                    } else System.out.println("error");
                    break;
                case "Update":
                    int newRarity = Integer.parseInt(parts[2]);
                    if (map.containsKey(plant)) {
                        map.put(plant, map.get(plant).setRarity(newRarity));
                    } else System.out.println("error");
                    break;
                case "Reset":
                    map.get(plant).setRatings(new ArrayList<>());
                default:
                    System.out.println("error");
            }
            input = scanner.nextLine();
        }
        //The plants should be sorted by rarity in descending order.
        // If two or more plants have the same rarity value sort them by average rating in descending order.
        // The average rating should be formatted to the second decimal place.
        System.out.println("Plants for the exhibition:");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println("- " + e.getKey() + "; " + e.getValue().toString()));
    }

    static class Plant implements Comparable<Plant> {
        private int rarity;
        List<Integer> ratings;

        public Plant(int rarity, List<Integer> ratings) {
            this.rarity = rarity;
            this.ratings = ratings;
        }

        @Override
        public String toString() {
            return String.format("Rarity: %d; Rating: %.2f", rarity, getAvgRating());
        }

        public double getAvgRating() {
            return ratings.stream().mapToInt(Integer::intValue).average().orElse(0);
        }

        public Plant setRarity(int rarity) {
            this.rarity = rarity;
            return this;
        }

        public int getRarity() {
            return rarity;
        }

        public Plant setRatings(List<Integer> ratings) {
            this.ratings = ratings;
            return this;
        }

        public Plant addRating(int n) {
            ratings.add(n);
            return this;
        }

        public List<Integer> getRatings() {
            return ratings;
        }

        @Override
        public int compareTo(Plant plant) {
            //The plants should be sorted by rarity in descending order.
            // If two or more plants have the same rarity value sort them by average rating in descending order.
            // The average rating should be formatted to the second decimal place.
            if (!(this.rarity == plant.getRarity())) {
                if (this.rarity > plant.getRarity()) {
                    return -1;
                } else return 1;
            } else {
                if (this.getAvgRating() > plant.getAvgRating()) {
                    return -1;
                } else return 1;
            }
        }
    }
}