package com.TextProcessing.Exercise.ExtractFile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        String filename = path.split("\\\\")[path.split("\\\\").length - 1];
        System.out.println("File name: " + filename.split("\\.")[0]);
        System.out.println("File extension: " + filename.split("\\.")[1]);
    }
}
