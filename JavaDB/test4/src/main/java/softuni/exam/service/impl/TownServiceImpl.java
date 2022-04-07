package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownDTO;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

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
    ModelMapper mapper;
    Gson gson;
    TownRepository townRepository;
    Validator validator;

    public TownServiceImpl(ModelMapper mapper,
                           Gson gson,
                           TownRepository townRepository,
                           Validator validator) {
        this.mapper = mapper;
        this.gson = gson;
        this.townRepository = townRepository;
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
        List<String> result = new ArrayList<>();
        String json = readTownsFileContent();
        TownDTO[] townDTOS = gson.fromJson(json, TownDTO[].class);
        for (TownDTO townDTO : townDTOS) {
            if (validator.validate(townDTO).isEmpty() &&
                    !(townRepository.existsByName(townDTO.getName()))) {
                result.add(String.format("Successfully imported Town %s - %d",
                        townDTO.getName(),
                        townDTO.getPopulation()));
                Town town = mapper.map(townDTO, Town.class);
                townRepository.save(town);
            } else {
                result.add("Invalid Town");
            }
        }
        return String.join("\n", result);
    }
}
