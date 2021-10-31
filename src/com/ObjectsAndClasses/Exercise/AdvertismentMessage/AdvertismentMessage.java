package com.ObjectsAndClasses.Exercise.AdvertismentMessage;

import java.util.Random;
import java.util.Scanner;

public class AdvertismentMessage {

    public static void main(String[] args) {
        String[] phrases = {"Excellent product.", "Such a great product.", "I always use that product.", "Best product of its category.", "Exceptional product.", "I can’t live without this product."};
        String[] events  = {"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
        String[] authors = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String[] cities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String phrase = phrases[new Random().nextInt(phrases.length-1)];
            String event = events[new Random().nextInt(events.length-1)];
            String author = authors[new Random().nextInt(authors.length-1)];
            String city = cities[new Random().nextInt(cities.length-1)];
            Message message = new Message(phrase, event, author, city);
            System.out.println(message.toString());
        }
    }
}
