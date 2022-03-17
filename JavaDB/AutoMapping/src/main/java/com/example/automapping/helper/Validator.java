package com.example.automapping.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validImageURL(String imageUrl) {
        boolean valid = imageUrl.matches("https*:\\/\\/");
        if (!valid) System.out.println("Thumbnail URL – it should be a plain text starting with http://, https:// ");
        return valid;
    }

    public static String cropYTUrl(String trailer) {
        Pattern pattern = Pattern.compile("(https:\\/\\/www\\.)*youtube\\.com\\/watch\\?v=(?<url>\\w{11})");
        Matcher matcher = pattern.matcher(trailer);
        if (!matcher.matches()) {
            System.out.println("invalid youtube url");
            return null;
        }
        return matcher.group("url");
    }

    public static boolean validTrailer(String trailer) {
        Pattern pattern = Pattern.compile("(https:\\/\\/www\\.)*youtube\\.com\\/watch\\?v=(?<url>\\w{11})");
        Matcher matcher = pattern.matcher(trailer);
        if (!matcher.matches()) {
            System.out.println("invalid youtube url");
            return false;
        }
        return true;
    }

    public static Optional<LocalDate> validReleaseDate(String releaseDate) {
        Pattern pattern = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})(?<day>\\d{2})");
        Matcher matcher = pattern.matcher(releaseDate);
        if (matcher.matches()) {
            int year = Integer.parseInt(matcher.group("year"));
            int day = Integer.parseInt(matcher.group("day"));
            int month = Integer.parseInt(matcher.group("month"));
            return Optional.of(LocalDate.of(year, month, day));
        }
        System.out.println("date must be in format yyyy-mm-dd");
        return Optional.empty();
    }

    public static boolean validTitle(String title) {
        boolean valid = size(title, 3, 100) || title.matches("[A-Z]+");
        if (!valid)
            System.out.println("Title – has to begin with an uppercase letter and must have length between 3 and 100 symbols (inclusively).");
        return valid;
    }

    public static boolean validPrice(BigDecimal price) {
        boolean valid = !(price.doubleValue() > 0);
        if (!valid) System.out.println("Price – must be a positive number.");
        return valid;
    }

    public static boolean validSize(Long size) {
        boolean valid = size > 0;
        if (!valid) System.out.println("Size – must be a positive number.");
        return valid;
    }

    public static boolean validDescription(String description) {
        boolean valid = description.length() >= 20;
        if (!valid) System.out.println("Description – must be at least 20 symbols");
        return valid;
    }

    public static boolean size(String text, int min, int max) {
        return text.length() >= min && text.length() <= max;
    }
}
