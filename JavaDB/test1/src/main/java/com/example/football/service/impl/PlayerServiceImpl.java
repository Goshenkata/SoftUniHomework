package com.example.football.service.impl;

import com.example.football.models.dto.BestPlayerDTO;
import com.example.football.models.dto.PlayerDTO;
import com.example.football.models.dto.PlayersDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Position;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {

    Validator validator;
    ModelMapper mapper;
    PlayerRepository playerRepository;
    StatRepository statRepository;
    TownRepository townRepository;
    TeamRepository teamRepository;

    public PlayerServiceImpl(Validator validator,
                             ModelMapper mapper,
                             PlayerRepository playerRepository,
                             StatRepository statRepository,
                             TownRepository townRepository,
                             TeamRepository teamRepository
    ) {
        this.validator = validator;
        this.mapper = mapper;
        this.playerRepository = playerRepository;
        this.statRepository = statRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "players.xml");
        return Files.readString(path);
    }

    @Override
    public String importPlayers() throws IOException, JAXBException {
        StringReader stringReader = new StringReader(readPlayersFileContent());
        JAXBContext context = JAXBContext.newInstance(PlayersDTO.class);
        List<String> result = new ArrayList<>();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PlayersDTO playersDTO = (PlayersDTO) unmarshaller.unmarshal(stringReader);
        for (PlayerDTO playerDTO : playersDTO.getPlayers()) {
            Set<ConstraintViolation<PlayerDTO>> validate = validator.validate(playerDTO);
            if (validate.isEmpty()) {
                if (!playerRepository.existsByEmail(playerDTO.getEmail())) {
                    if (statRepository.existsById(playerDTO.getStat().getId())) {
                        Player player = mapper.map(playerDTO, Player.class);
                        int[] dateParts = Arrays.stream(playerDTO
                                        .getBirthDate()
                                        .split("/"))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        LocalDate birthday = LocalDate.of(dateParts[2], dateParts[1], dateParts[0]);
                        player.setBirthDate(birthday);
                        player.setStat(statRepository.getById(playerDTO.getStat().getId()));
                        Position position = switch (playerDTO.getPosition()) {
                            case "DEF" -> Position.DEF;
                            case "ATT" -> Position.ATT;
                            case "MID" -> Position.MID;
                            default -> throw new IllegalStateException("Unexpected value: " + playerDTO.getPosition());
                        };
                        player.setPosition(position);
                        player.setTown(townRepository.getByName(playerDTO.getTown().getName()));
                        player.setTeam(teamRepository.getByName(player.getTeam().getName()));
                        playerRepository.save(player);
                        result.add(String.format("Successfully imported Player %s %s - %s",
                                playerDTO.getFirstName(),
                                playerDTO.getLastName(),
                                playerDTO.getPosition()));
                    } else result.add("Invalid Stat");
                } else result.add("Invalid Player");
            } else result.add("Invalid Player");
        }
        return String.join("\n", result);
    }

    @Override
    public String exportBestPlayers() {
        return playerRepository
                .getBestPlayers()
                .stream()
                .map(BestPlayerDTO::toString)
                .collect(Collectors.joining("\n"));
    }
}
