package exam.service.impl;

import exam.model.DTO.TownDTO;
import exam.model.DTO.TownsDTO;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TownServiceImpl implements TownService {
    ModelMapper mapper;
    TownRepository townRepository;
    Validator validator;

    public TownServiceImpl(ModelMapper mapper,
                           TownRepository townRepository,
                           Validator validator) {
        this.mapper = mapper;
        this.townRepository = townRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "towns.xml");
        return Files.readString(path);
    }

    @Override
    public String importTowns() throws JAXBException, IOException {
        StringReader xml = new StringReader(readTownsFileContent());
        JAXBContext context = JAXBContext.newInstance(TownsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TownsDTO townsDTO = (TownsDTO) unmarshaller.unmarshal(xml);
        List<String> result = new ArrayList<>();
        for (TownDTO townDTO : townsDTO.getTowns()) {
            Set<ConstraintViolation<TownDTO>> validate = validator.validate(townDTO);
            if (validate.isEmpty()) {
                if (!townRepository.existsByName(townDTO.getName())) {
                    Town town = mapper.map(townDTO, Town.class);
                    result.add("Successfully imported Town " + townDTO.getName());
                    townRepository.save(town);
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
