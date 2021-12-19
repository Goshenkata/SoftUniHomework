package com.AssociativeArraysLambdaStreamAPI.MoreExrcise.SnowWhite;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Dwarf, Integer> map = new LinkedHashMap<>();
        Map<String, Integer> colorCount = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("Once upon a time")) {
            String[] data = input.split(" <:> ");
            String name = data[0];
            String color = data[1];
            int physics = Integer.parseInt(data[2]);
            Dwarf dwarf = new Dwarf(name, color);
            if (map.containsKey(dwarf)) {
                if (map.get(dwarf) < physics) {
                    map.put(dwarf, physics);
                    colorCount = UpdateColors(colorCount, color);
                }
            } else {
                map.put(dwarf, physics);
                colorCount.put(color, physics);
            }
            input = scanner.nextLine();
            //You must order the dwarfs by physics in descending order
            // and then by total count of dwarfs with the same hat color
        }
        Map<String, Integer> finalColorCount = colorCount;
        map.entrySet().stream()
                .sorted(Map.Entry.<Dwarf, Integer>comparingByValue()
                        .thenComparingInt(e -> finalColorCount.get(e.getKey().getHatColor())).reversed())
                .forEach(k -> System.out.printf("(%s) %s <-> %d%n", k.getKey().getHatColor(), k.getKey().getName(), k.getValue()));
    }

    private static Map<String, Integer> UpdateColors(Map<String, Integer> colorCount, String color) {
        if (colorCount.containsKey(color)) {
            colorCount.put(color, colorCount.get(color) + 1);
        } else {
            colorCount.put(color, 1);
        }
        return colorCount;
    }


    static class Dwarf {
        String name;
        String hatColor;

        @Override
        public boolean equals(Object o) {
            Dwarf dwarf = (Dwarf) o;

            if (!name.equals(dwarf.name)) return false;
            return hatColor.equals(dwarf.hatColor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, hatColor);
        }

        public String getName() {
            return name;
        }

        public Dwarf setName(String name) {
            this.name = name;
            return this;
        }

        public String getHatColor() {
            return hatColor;
        }

        public Dwarf setHatColor(String hatColor) {
            this.hatColor = hatColor;
            return this;
        }

        public Dwarf(String name, String hatColor) {
            this.name = name;
            this.hatColor = hatColor;
        }
    }
}
