package com.exams.exam2.HeroesOfCodeAndLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String heroName = input.split(" ")[0];
            int hp = Integer.parseInt(input.split(" ")[1]);
            int mp = Integer.parseInt(input.split(" ")[2]);
            Hero newHero = new Hero(heroName, hp, mp);
            if (!contains(heroes, heroName)) {
                heroes.add(new Hero(heroName, hp, mp));
            } else {
                heroes = put(heroes, newHero);
            }
        }
        String input = scanner.nextLine();
        while (!input.equals("End")) {
            switch (input.split(" - ")[0]) {
                case "CastSpell":
                    String name = input.split(" - ")[1];
            }
        }
    }

    private static List<Hero> put(List<Hero> heroes, Hero newHero) {
        heroes.remove(get(heroes, newHero.getName()));
        heroes.add(newHero);
        return heroes;
    }

    private static Hero get(List<Hero> heroes, String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {
                return hero;
            }
        }
        return null;
    }

    public static boolean contains(List<Hero> list, String name) {
        for (Hero hero : list) {
            if (hero.getName().equals(name)) return true;
        }
        return false;
    }

    static class Hero {
        String name;
        int gp;
        int mp;

        public Hero(String name, int gp, int mp) {
            this.name = name;
            this.gp = gp;
            this.mp = mp;
        }


        public String getName() {
            return name;
        }

        public int getGp() {
            return gp;
        }

        public int getMp() {
            return mp;
        }

        public Hero setName(String name) {
            this.name = name;
            return this;
        }

        public Hero setGp(int gp) {
            this.gp = gp;
            return this;
        }

        public Hero setMp(int mp) {
            this.mp = mp;
            return this;
        }
    }
}
