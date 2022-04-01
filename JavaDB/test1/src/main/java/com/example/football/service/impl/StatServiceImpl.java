package com.example.football.service.impl;

import com.example.football.models.dto.StatDTO;
import com.example.football.models.dto.StatsDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.SocketHandler;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {

    StatRepository statRepository;
    Validator validator;
    public ModelMapper mapper;

    public StatServiceImpl(StatRepository statRepository,
                           Validator validator,
                           ModelMapper mapper) {
        this.statRepository = statRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "stats.xml");
        return Files.readString(path);
    }

    @Override
    public String importStats() throws JAXBException, IOException {
        String xml = readStatsFileContent();
        JAXBContext context = JAXBContext.newInstance(StatsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader stringReader = new StringReader(xml);
        StatsDTO statsDTO = (StatsDTO) unmarshaller.unmarshal(stringReader);
        List<String> result = new ArrayList<>();
        for (StatDTO statDTO : statsDTO.getStats()) {
            Set<ConstraintViolation<StatDTO>> validate = validator.validate(statDTO);
            if (validate.isEmpty()) {
                if (!statRepository.existsByEnduranceAndPassingAndShooting(
                        statDTO.getEndurance(),
                        statDTO.getPassing(),
                        statDTO.getShooting()
                )) {
                    Stat stat = mapper.map(statDTO, Stat.class);
                    statRepository.save(stat);
                    result.add(String.format("Successfully imported Stat %.2f %.2f %.2f",
                            stat.getPassing(),
                            stat.getShooting(),
                            stat.getEndurance()));
                } else result.add("Invalid Stat");
            } else result.add("Invalid Stat");
        }
        return String.join("\n", result);
    }
}
