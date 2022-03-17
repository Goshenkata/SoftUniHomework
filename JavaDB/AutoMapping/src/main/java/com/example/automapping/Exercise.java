package com.example.automapping;

import com.example.automapping.DTO.LoggedInUser;
import com.example.automapping.config.GameDTO;
import com.example.automapping.service.GamesService;
import com.example.automapping.service.UserService;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise implements CommandLineRunner {

    UserService userService;
    LoggedInUser loggedInUser;
    GamesService gamesService;

    public Exercise(UserService userService, LoggedInUser loggedInUser, GamesService gamesService) {
        this.userService = userService;
        this.loggedInUser = loggedInUser;
        this.gamesService = gamesService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String commands[] = scanner.nextLine().split("\\|");
        switch (commands[0]) {
            case "RegisterUser":
                registerUser(commands);
                break;
            case "LoginUser":
                loginUser(commands);
                break;
            case "Logout":
                loggedInUser = null;
                break;
            case "AddGame":
                addGame(commands);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commands[0]);
        }
    }

    private void addGame(String[] commands) {
        String title = commands[1];
        BigDecimal price = new BigDecimal(commands[2]);
        long size = Long.parseLong(commands[3]);
        String trailer = commands[4];
        String imageUrl = commands[5];
        String description = commands[6];
        String releaseDate = commands[6];
        Optional<LocalDate> localDate = validReleaseDate(releaseDate);
        if (localDate.isPresent()) {
            if (validTitle(title) &&
                    validPrice(price) &&
                    validSize(size) &&
                    validTrailer(trailer) &&
                    validImageURL(imageUrl)
            ) {

            }
            boolean validUrl = validTrailer(trailer);
            if (validUrl) {
                GameDTO gameDTO = new GameDTO();
                gameDTO
                        .setDescription(description)
                        .setImageUrl(imageUrl)
                        .setPrice(price)
                        .setSize(size)
                        .setTitle(title)
                        .setTrailer(matcher.group("url"))
                        .setReleaseDate(localDate.get());
                validateGameAndRegisterGame(gameDTO);
            }
        }

    }

    private boolean validImageURL(String imageUrl) {
        
    }

    private boolean validTrailer(String trailer) {
        Pattern pattern = Pattern.compile("(https:\\/\\/www\\.)*youtube\\.com\\/watch\\?v=(?<url>\\w{11})");
        Matcher matcher = pattern.matcher(trailer);
        if (!matcher.matches()) {
            System.out.println("invalid youtube url");
            return false;
        }
        return true;
    }

    private Optional<LocalDate> validReleaseDate(String releaseDate) {
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

    public boolean validTitle(String title) {
        boolean valid = size(title, 3, 100) || title.matches("[A-Z]+");
        if (!valid)
            System.out.println("Title – has to begin with an uppercase letter and must have length between 3 and 100 symbols (inclusively).");
        return valid;
    }

    public boolean validPrice(BigDecimal price) {
        boolean valid = !(price.doubleValue() > 0);
        if (!valid) System.out.println("Price – must be a positive number.");
        return valid;
    }

    public boolean validSize(Long size) {
        boolean valid = size > 0;
        if (!valid) System.out.println("Size – must be a positive number.");
        return valid;
    }

    public boolean validDescription(String description) {
        boolean valid = description.length() >= 20;
        if (!valid) System.out.println("Description – must be at least 20 symbols");
        return valid;
    }

    public boolean size(String text, int min, int max) {
        return text.length() >= min && text.length() <= max;
    }


    private void loginUser(String[] commands) {
        String email = commands[1];
        String password = commands[2];
        this.loggedInUser = userService.loginUser(email, password);
    }

    public void registerUser(String[] commands) {
        String email = commands[1];
        String password = commands[2];
        String confirmPassword = commands[3];
        String fullName = commands[4];
        if (isEmailValid(email) && password.equals(confirmPassword)) {
            userService.registerUser(email, password, fullName);
        }
    }

    private boolean isEmailValid(String email) {
        if (!email.contains("@") || email.split("@")[1].contains(".")) {
            System.out.println("Email – must contain @ sign and a period. It must be unique.");
            return false;
        }
        if (userService.containsEmail(email)) {
            System.out.println("email is already used");
        }
        return true;
    }
}
