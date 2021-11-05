package com.ObjectsAndClasses.Exercise.VehicleCatalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Vehicle> cars = new ArrayList<>();
        List<Vehicle> trucks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("End")) {
            String[] commands = input.split(" ");
            if (commands[0].equals("car")) {
                cars.add(new Vehicle("Car", commands[1], commands[2], Integer.parseInt(commands[3])));
            } else {
                trucks.add(new Vehicle("Truck", commands[1], commands[2], Integer.parseInt(commands[3])));
            }
            input = scanner.nextLine();
        }
        input = scanner.nextLine();
        while (!input.equalsIgnoreCase("Close the Catalogue")) {
            for (Vehicle car : cars) {
                if (car.getModel().equalsIgnoreCase(input)) {
                    System.out.println(car);
                }
            }
            for (Vehicle truck : trucks) {
                if (truck.getModel().equalsIgnoreCase(input)) {
                    System.out.println(truck);
                }
            }
            input = scanner.nextLine();
        }
        int sumHorsepowerCars = cars.stream()
                .map(Vehicle::getHorsepower)
                .mapToInt(Integer::intValue)
                .sum();
        int sumHorsepowerTrucks =  trucks.stream()
                .map(Vehicle::getHorsepower)
                .mapToInt(Integer::intValue)
                .sum();
        if (!cars.isEmpty()) {
            System.out.printf("Cars have average horsepower of: %.2f.%n", sumHorsepowerCars * 1.0 / cars.size());
        } else {
            System.out.println("Cars have average horsepower of: 0.00.");
        }
        if (!trucks.isEmpty()) {
            System.out.printf("Trucks have average horsepower of: %.2f.%n", sumHorsepowerTrucks * 1.0 / trucks.size());
        } else {
            System.out.println("Trucks have average horsepower of: 0.00.");
        }
    }
}
