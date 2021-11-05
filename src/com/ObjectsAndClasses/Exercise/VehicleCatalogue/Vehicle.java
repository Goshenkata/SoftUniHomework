package com.ObjectsAndClasses.Exercise.VehicleCatalogue;

public class Vehicle {
    private final String type;
    private final String model;
    private final String color;
    private final int horsepower;

    public Vehicle(String type, String model, String color, int horsepower) {
        this.type = type;
        this.model = model;
        this.color = color;
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return String.format("Type: %s%nModel: %s%nColor: %s%nHorsepower: %d",type,model,color, horsepower);
    }

    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }
}
