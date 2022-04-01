package com.example.football.service.impl;

import com.example.football.models.dto.TownDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class TownServiceImpl implements TownService {

    TownRepository townRepository;
    Gson gson;
    ModelMapper mapper;
    Validator validator;

    public TownServiceImpl(TownRepository townRepository,
                           Gson gson,
                           ModelMapper mapper,
                           Validator validator) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "towns.json");
        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();
        List<String> result = new ArrayList<>();

        TownDTO[] townDTOS = gson.fromJson(json, TownDTO[].class);
        for (TownDTO townDTO : townDTOS) {
            Town town = mapper.map(townDTO, Town.class);
            Set<ConstraintViolation<TownDTO>> violations = validator.validate(townDTO);
            if (violations.isEmpty()) {
                if (!townRepository.existsByName(town.getName())) {
                    townRepository.save(town);
                    result.add(String.format("Successfully imported Town %s - population %d",town.getName(), town.getPopulation()));
                } else {
                    result.add("Invalid Town");
                }
            } else {
                result.add("Invalid Town");
            }
        }

        return String.join("\n", result);
    }
}
