package com.Regex.exercise.NetherRealms;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] demons = scanner.nextLine().split(", ");
        List<Demon> demonList = new ArrayList<>();
        for (String demon : demons) {
            int demonHealth = caclHealth(demon);
            double damage = calcDamage(demon);
            demonList.add(new Demon(demon, demonHealth, damage));
        }
        demonList.stream()
                .sorted(Comparator.comparing(Demon::getName))
                .forEach(System.out::println);

    }

    private static double calcDamage(String demon) {
        double damage = 0;
        Pattern pattern = Pattern.compile("[\\-]?\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(demon);

        while (matcher.find()) {
            double number = Double.parseDouble(matcher.group());
            damage += number;
        }

        pattern = Pattern.compile("[/*]{1}");
        matcher = pattern.matcher(demon);

        while (matcher.find()) {
            String symbol = matcher.group();
            if (symbol.equals("/")) {
                damage /= 2;
            }
        }

        return damage;
    }

    private static int caclHealth(String demon) {
        Pattern pattern = Pattern.compile("[^\\d+\\-=*\\/.]{1}");
        int health = 0;
        Matcher matcher = pattern.matcher(demon);
        while (matcher.find()) {
            health += matcher.group().charAt(0);
        }
        return health;
    }

    static class Demon {
        String name;
        int health;
        double damage;

        public Demon(String name, int health, double damage) {
            this.name = name;
            this.health = health;
            this.damage = damage;
        }

        public String getName() {
            return name;
        }

        public Demon setName(String name) {
            this.name = name;
            return this;
        }

        public int getHealth() {
            return health;
        }

        public Demon setHealth(int health) {
            this.health = health;
            return this;
        }

        public double getDamage() {
            return damage;
        }

        public Demon setDamage(double damage) {
            this.damage = damage;
            return this;
        }

        @Override
        public String toString() {
            return String.format("%s- %d health, %.2f damage", name, health, damage);
        }
    }

}