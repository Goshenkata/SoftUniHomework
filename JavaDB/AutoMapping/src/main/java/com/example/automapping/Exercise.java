package com.example.automapping;

import com.example.automapping.DTO.LoggedInUser;
import com.example.automapping.config.GameDTO;
import com.example.automapping.service.GamesService;
import com.example.automapping.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import static com.example.automapping.helper.Validator.*;
import static java.lang.Long.parseLong;

@Component
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
        String[] commands = scanner.nextLine().split("\\|");

        while (!commands[0].equals("exit")) {
            switch (commands[0]) {
                case "RegisterUser":
                    registerUser(commands);
                    break;
                case "LoginUser":
                    loginUser(commands);
                    break;
                case "Logout":
                    if (loggedInUser.getEmail() == null) {
                        System.out.println("Cannot log out. No user was logged in.");
                    } else {
                        System.out.printf("User %s successfully logged out%n", loggedInUser.getFullName().split(" ")[0]);
                        loggedInUser = null;
                    }
                    break;
                case "AddGame":
                    if (loggedInUser.getEmail() != null && loggedInUser.isAdmin()) {
                        addGame(commands);
                    } else System.out.println("Must be admin to perform this action");
                    break;
                case "EditGame":
                    if (loggedInUser.getEmail() != null && loggedInUser.isAdmin()) {
                        edit(commands);
                    } else System.out.println("Must be admin to perform this action");
                    break;
                case "DeleteGame":
                    Long id = parseLong(commands[1]);
                    gamesService.delete(id);
                    break;
                case "AllGames":
                    gamesService.getAllGames();
                    break;
                case "DetailGame":
                    gamesService.getInfoByGame(commands[1]);
                    break;
                case "OwnedGames":
                    if (loggedInUser.getEmail() != null) {
                        userService.getGames(loggedInUser.getEmail());
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + commands[0]);
            }
            commands = scanner.nextLine().split("\\|");
        }
    }


    private void edit(String[] commands) {
        Long id = parseLong(commands[1]);
        if (gamesService.gameExist(id)) {
            for (int i = 2; i < commands.length; i++) {
                String value = commands[i];
                gamesService.edit(value, id);
            }
        } else {
            System.out.println("No game with id");
        }
    }

    private void addGame(String[] commands) {
        String title = commands[1];
        BigDecimal price = new BigDecimal(commands[2]);
        long size = parseLong(commands[3]);
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
                    validImageURL(imageUrl) &&
                    validDescription(description)
            ) {
                GameDTO gameDTO = new GameDTO();
                gameDTO
                        .setDescription(description)
                        .setImageUrl(imageUrl)
                        .setPrice(price)
                        .setSize(size)
                        .setTitle(title)
                        .setTrailer(cropYTUrl(trailer))
                        .setReleaseDate(localDate.get());
            }
        }

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
            System.out.println(fullName.split(" ")[0] + " was registered");
        }
    }

    private boolean isEmailValid(String email) {
        if (!(email.contains("@") && email.split("@")[1].contains("."))) {
            System.out.println("Incorrect email.");
            return false;
        }
        if (userService.containsEmail(email)) {
            System.out.println("email is already used");
            return false;
        }
        return true;
    }
}
