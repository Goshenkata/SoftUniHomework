package com.AssociativeArraysLambdaStreamAPI.Exercise.Orders;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Product> map = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("buy")) {
            String name = input.split(" ")[0];
            double price = Double.parseDouble(input.split(" ")[1]);
            int count = Integer.parseInt(input.split(" ")[2]);
            if (map.containsKey(name)) {
                map.put(name, new Product(price, map.get(name).getCount() + count));
            } else map.put(name, new Product(price, count));
            input = scanner.nextLine();
        }
        map.forEach((key, value) -> System.out.printf("%s -> %.2f%n", key, value.getTotalPrice()));
    }
    static class Product {
        private final double price;
        private final int count;

        public Product(double price, int count) {
            this.price = price;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public double getTotalPrice() {
            return price*count;
        }

    }
}
