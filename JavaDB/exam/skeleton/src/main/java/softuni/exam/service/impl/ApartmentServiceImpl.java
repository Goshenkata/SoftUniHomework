package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentDTO;
import softuni.exam.models.dto.ApartmentsDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

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
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    TownRepository townRepository;
    ApartmentRepository apartmentRepository;
    Validator validator;

    public ApartmentServiceImpl(TownRepository townRepository,
                                ApartmentRepository apartmentRepository,
                                Validator validator) {
        this.townRepository = townRepository;
        this.apartmentRepository = apartmentRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "apartments.xml");
        return Files.readString(path);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        List<String> result = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(ApartmentsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader stringReader = new StringReader(readApartmentsFromFile());
        ApartmentsDTO apartmentsDTO = (ApartmentsDTO) unmarshaller.unmarshal(stringReader);
        for (ApartmentDTO apartmentDTO : apartmentsDTO.getApartments()) {
            List<Apartment> byTownAndArea = apartmentRepository.findByTownAndArea(apartmentDTO.getTown(), apartmentDTO.getArea());
            if (validator.validate(apartmentDTO).isEmpty() && byTownAndArea.isEmpty()) {

                Apartment apartment = new Apartment();
                ApartmentType apartmentType = switch (apartmentDTO.getApartmentType()) {
                    case "two_rooms" -> ApartmentType.TWO_ROOMS;
                    case "three_rooms" -> ApartmentType.THREE_ROOMS;
                    case "four_rooms" -> ApartmentType.FOUR_ROOMS;
                    default -> throw new IllegalArgumentException();
                };
                apartment.setApartmentType(apartmentType);
                apartment.setArea(apartmentDTO.getArea());
                apartment.setTown(townRepository.getByTownName(apartmentDTO.getTown()));
                result.add(String.format("Successfully imported apartment %s - %.2f",
                        apartmentDTO.getApartmentType(),
                        apartmentDTO.getArea()));
                apartmentRepository.save(apartment);
            } else {
                result.add("Invalid apartment");
            }
        }

        return String.join("\n", result);
    }
}
