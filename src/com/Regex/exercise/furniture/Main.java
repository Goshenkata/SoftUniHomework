package com.Regex.exercise.furniture;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> furnitures = new ArrayList<>();
        double total = 0;
        Pattern pattern = Pattern.compile(">>(?<furniture>[A-Za-z]+)<<(?<price>[\\d]+.*[\\d]*)!(?<quantity>\\d*)");
        while (!input.equals("Purchase")) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String furniture = matcher.group("furniture");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));
                total += price*quantity;
                furnitures.add(furniture);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bought furniture:");
        furnitures.forEach(System.out::println);
        System.out.printf("Total money spend: %.2f", total);
    }

}
    