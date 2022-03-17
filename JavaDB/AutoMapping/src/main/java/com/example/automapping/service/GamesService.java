package com.example.automapping.service;

import com.example.automapping.config.GameDTO;
import com.example.automapping.entities.Games;

import static com.example.automapping.helper.Validator.*;

import com.example.automapping.repository.GamesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class GamesService {

    GamesRepository gamesRepository;
    ModelMapper modelMapper;

    public GamesService(GamesRepository gamesRepository, ModelMapper modelMapper) {
        this.gamesRepository = gamesRepository;
        this.modelMapper = modelMapper;
    }

    public void save(GameDTO gameDTO) {
        gamesRepository.save(modelMapper.map(gameDTO, Games.class));
    }

    public boolean gameExist(Long id) {
        return gamesRepository.findById(id).isPresent();
    }

    public void edit(String value, Long id) {
        String[] commands = value.split("=");
        String toChange = commands[0];
        String newValue = commands[1];
        Optional<Games> gameOpt = gamesRepository.findById(id);
        if (gameOpt.isPresent()) {
            Games game = gameOpt.get();
            switch (newValue) {
                case "title":
                    if (validTitle(newValue)) {
                        game.setTitle(newValue);
                    }
                    break;
                case "price":
                    BigDecimal price = new BigDecimal(value);
                    if (validPrice(price)) {
                        game.setPrice(price);
                    }
                    break;
                case  "size":
                    Long size = Long.parseLong(newValue);
                    if (validSize(size)) {
                        game.setSize(size);
                    }
                    break;
                case "trailer":
                    if (validTrailer(newValue)) {
                        game.setTrailer(cropYTUrl(newValue));
                    }
                    break;
                case "thumbnailURL":
                    if (validImageURL(newValue)) {
                        game.setImageUrl(newValue);
                    }
                    break;
                case "desciprion":
                    if (validDescription(newValue)) {
                        game.setDescription(newValue);
                    }
                    break;
                case "releaseDate":
                    Optional<LocalDate> localDate = validReleaseDate(newValue);
                    localDate.ifPresent(game::setReleaseDate);
                    break;
                default:
                    System.out.println("invalid value");
            }
            gamesRepository.save(game);
        } else System.out.println("invalid game id");
    }

    public void delete(Long id) {
        Optional<Games> gameOpt = gamesRepository.findById(id);
        if (gameOpt.isPresent()) {
            gamesRepository.delete(gameOpt.get());
        } else {
            System.out.println("No game with such id");
        }
    }

    public void getAllGames() {
        gamesRepository
                .findAll()
                .stream()
                .map(g -> String.format("%s %.2f", g.getTitle(), g.getPrice().doubleValue()))
                .forEach(System.out::println);
    }

    public void getInfoByGame(String command) {
        Optional<Games> byTitle = gamesRepository.findByTitle(command);
        if (byTitle.isPresent()) {
            Games game = byTitle.get();
            System.out.printf("Title: %s%nPrice: %.2f%s%nDesription: %s%nReleaseDate %s",
                    game.getTitle(),
                    game.getPrice().doubleValue(),
                    game.getDescription(),
                    game.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } else System.out.println("No game with name " + command);
    }

}
