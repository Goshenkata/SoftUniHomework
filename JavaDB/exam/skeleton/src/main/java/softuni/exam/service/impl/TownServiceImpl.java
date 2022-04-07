package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class TownServiceImpl implements TownService {
    Gson gson;
    Validator validator;
    ModelMapper mapper;
    TownRepository townRepository;

    public TownServiceImpl(Gson gson,
                           Validator validator,
                           ModelMapper mapper,
                           TownRepository townRepository) {
        this.gson = gson;
        this.validator = validator;
        this.mapper = mapper;
        this.townRepository = townRepository;
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
        String json = readTownsFileContent();
        List<String> result = new ArrayList<>();
        TownDTO[] townDTOS = gson.fromJson(json, TownDTO[].class);
        for (TownDTO townDTO : townDTOS) {
            if (validator.validate(townDTO).isEmpty() &&
                !(townRepository.existsByTownName(townDTO.getTownName()))) {
                result.add(String.format("Successfully imported town %s - %d",
                        townDTO.getTownName(),
                        townDTO.getPopulation()));
                Town town = mapper.map(townDTO, Town.class);
                townRepository.save(town);
            } else {
                result.add("Invalid town");
            }
        }
        return String.join("\n", result);
    }
}
