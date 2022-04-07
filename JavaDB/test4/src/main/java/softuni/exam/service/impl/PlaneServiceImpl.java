package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PlaneDTO;
import softuni.exam.models.dto.PlanesDTO;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.image.Kernel;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {
    ModelMapper mapper;
    Validator validator;
    PlaneRepository planeRepository;

    public PlaneServiceImpl(ModelMapper mapper, Validator validator, PlaneRepository planeRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.planeRepository = planeRepository;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "planes.xml");
        return Files.readString(path);
    }

    @Override
    public String importPlanes() throws IOException, JAXBException {
        List<String> result = new ArrayList<>();
        StringReader stringReader = new StringReader(readPlanesFileContent());
        JAXBContext context = JAXBContext.newInstance(PlanesDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PlanesDTO planesDTO = (PlanesDTO) unmarshaller.unmarshal(stringReader);
        for (PlaneDTO planeDTO : planesDTO.getPlaneDTOList()) {
            if (validator.validate(planeDTO).isEmpty()) {
                Plane plane = mapper.map(planeDTO, Plane.class);
                result.add("Successfully imported Plane " + plane.getRegisterNumber());
                planeRepository.save(plane);
            } else {
                result.add("Invalid Plane");
            }
        }
        System.out.println();

        return String.join("\n", result);
    }
}
