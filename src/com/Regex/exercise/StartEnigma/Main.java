package com.Regex.exercise.StartEnigma;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Pattern pattern = Pattern.compile("@(?<planet>[^@\\-!:]*[a-zA-Z]+)[^@\\-!:]*:(?<population>[^@\\-!:]*)!(?<attack>[AD])![^@\\-!:]*->(?<soldiers>\\d+)");
        List<String> attacked = new ArrayList<>();
        List<String> destroyed = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String encrypted = scanner.nextLine();
            String decrypt = decrypt(encrypted);
            Matcher matcher = pattern.matcher(decrypt);
            if (matcher.find()) {
                String planet = matcher.group("planet");
                int population = Integer.parseInt(matcher.group("population"));
                String attackType = matcher.group("attack");
                int soldiers = Integer.parseInt(matcher.group("soldiers"));
                if (attackType.equals("A")) {
                    attacked.add(planet);
                } else if (attackType.equals("D")) {
                    destroyed.add(planet);
                }
            }
        }
        System.out.println("Attacked planets: " + attacked.size());
        Collections.sort(attacked);
        Collections.sort(destroyed);
        attacked.forEach(p -> System.out.println("-> " + p));
        System.out.println("Destroyed planets: " + destroyed.size());
        destroyed.forEach(p -> System.out.println("-> " + p));
    }

    public static String decrypt(String text) {
        int countLetters = getLettersCount(text);
        StringBuilder decrypt = new StringBuilder();
        for (char chara : text.toCharArray()) {
            char decryptedChar = (char) (chara - countLetters);
            decrypt.append(decryptedChar);
        }

        return decrypt.toString();
    }

    private static int getLettersCount(String text) {
        int counter = 0;
        text = text.toLowerCase(Locale.ROOT);
        for (char chara : text.toCharArray()) {
            if (chara == 's' || chara == 't' || chara == 'a' || chara == 'r') {
                counter++;
            }
        }
        return counter;
    }

}
