package com.ObjectsAndClasses.Exercise.AdvertismentMessage;

public class Message {
    String phrase;
    String event;
    String author;
    String city;

    public Message(String phrase, String event, String author, String city) {
        this.phrase = phrase;
        this.event = event;
        this.author = author;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("%s %s %S - %s", phrase, event, author, city);
    }
}
