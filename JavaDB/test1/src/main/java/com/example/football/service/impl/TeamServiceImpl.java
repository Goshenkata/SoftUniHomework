package com.example.football.service.impl;

import com.example.football.models.dto.TeamDTO;
import com.example.football.models.dto.TownDTO;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

//ToDo - Implement all methods
@Service
public class TeamServiceImpl implements TeamService {

    TeamRepository teamRepository;
    TownRepository townRepository;
    Validator validator;
    Gson gson;
    ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository,
                           Gson gson,
                           ModelMapper modelMapper,
                           TownRepository townRepository,
                           Validator validator) {
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");
        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {
        List<String> results = new ArrayList<>();
        String json = this.readTeamsFileContent();
        TeamDTO[] teamDTOS = gson.fromJson(json, TeamDTO[].class);
        for (TeamDTO team : teamDTOS) {
            Team map = modelMapper.map(team, Team.class);
            map.setTown(townRepository.getByName(team.getTownName()));
            Set<ConstraintViolation<TeamDTO>> violations = validator.validate(team);
            if (violations.isEmpty()) {
                if (!teamRepository.existsByName(team.getName())) {
                   teamRepository.save(map);
                   results.add(String.format("Successfully imported Team %s - %d", map.getName(), map.getFanBase()));
                } else {
                    results.add("Invalid Team");
                }
            } else {
                results.add("Invalid Team");
            }
        }
        return String.join("\n", results);
    }
}
