package com.exams.exam2.HeroesOfCodeAndLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
            String command = input.split(" - ")[0];
            String name = input.split(" - ")[1];
            switch (command) {
                case "CastSpell":
                    int mpNeeded = Integer.parseInt(input.split(" - ")[2]);
                    String spellName = input.split(" - ")[3];
                    if (get(heroes, name).getMp() >= mpNeeded) {
                        heroes = put(heroes, get(heroes, name).setMp(get(heroes, name).getMp() - mpNeeded));
                        System.out.printf("%s has successfully cast %s and now has %d MP!%n", name, spellName, get(heroes, name).getMp());
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!%n", name, spellName);
                    }
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(input.split(" - ")[2]);
                    String attacker = input.split(" - ")[3];
                    if (get(heroes, name).getHp() - damage > 0) {
                        heroes = put(heroes, get(heroes, name).setHp(get(heroes, name).getHp() - damage));
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n", name, damage, attacker, get(heroes, name).getHp());
                    } else {
                        System.out.printf("%s has been killed by %s!%n", name, attacker);
                        heroes = remove(heroes, name);
                    }
                    break;
                case "Recharge":
                    int amount = Integer.parseInt(input.split(" - ")[2]);
                    if (get(heroes, name).getMp() + amount > 200) {
                        amount = 200 - get(heroes, name).getMp();
                        heroes = put(heroes, get(heroes, name).setMp(200));
                        System.out.printf("%s recharged for %d MP!%n", name, amount);
                    } else {
                        heroes = put(heroes, get(heroes, name).setMp(get(heroes, name).getMp() + amount));
                        System.out.printf("%s recharged for %d MP!%n", name, amount);
                    }
                    break;
                case "Heal":
                    int hpAmount = Integer.parseInt(input.split(" - ")[2]);
                    if (get(heroes, name).getHp() + hpAmount > 100) {
                        hpAmount = 100 - get(heroes, name).getHp();
                        heroes = put(heroes, get(heroes, name).setHp(100));
                        System.out.printf("%s healed for %d HP!%n", name, hpAmount);
                    } else {
                        heroes = put(heroes, get(heroes, name).setHp(get(heroes, name).getHp() + hpAmount));
                        System.out.printf("%s healed for %d HP!%n", name, hpAmount);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        heroes.stream()
                .sorted(Comparator.comparing(Hero::getHp).reversed()
                        .thenComparing(Hero::getName))
                .forEach(System.out::println);
    }

    private static List<Hero> remove(List<Hero> heroes, String name) {
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getName().equals(name)) {
                heroes.remove(i);
                break;
            }
        }
        return heroes;
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
        int hp;
        int mp;

        public Hero(String name, int hp, int mp) {
            this.name = name;
            this.hp = hp;
            this.mp = mp;
        }


        public String getName() {
            return name;
        }

        public int getHp() {
            return hp;
        }

        public int getMp() {
            return mp;
        }

        public Hero setName(String name) {
            this.name = name;
            return this;
        }

        public Hero setHp(int hp) {
            this.hp = hp;
            return this;
        }

        public Hero setMp(int mp) {
            this.mp = mp;
            return this;
        }

        @Override
        public String toString() {
            return String.format("%s\n" +
                    "  HP: %d\n" +
                    "  MP: %d", name, hp, mp);
        }
    }
}
