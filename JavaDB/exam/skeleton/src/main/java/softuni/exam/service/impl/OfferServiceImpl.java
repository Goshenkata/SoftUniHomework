package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferDTO;
import softuni.exam.models.dto.OfferExportDTO;
import softuni.exam.models.dto.OffersDTO;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

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
import java.util.stream.Collectors;

import static softuni.exam.util.UtilClass.toLocalDate;

@Service
public class OfferServiceImpl implements OfferService {
    ApartmentRepository apartmentRepository;
    OfferRepository offerRepository;
    AgentRepository agentRepository;
    Validator validator;

    public OfferServiceImpl(ApartmentRepository apartmentRepository,
                            OfferRepository offerRepository,
                            AgentRepository agentRepository,
                            Validator validator) {
        this.apartmentRepository = apartmentRepository;
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "offers.xml");
        return Files.readString(path);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        List<String> result = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(OffersDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader stringReader = new StringReader(readOffersFileContent());
        OffersDTO offersDTO = (OffersDTO) unmarshaller.unmarshal(stringReader);
        for (OfferDTO offerDTO : offersDTO.getOffers()) {
            String firstName = offerDTO.getAgentNameDTO().getFirstName();
            Long id = offerDTO.getApartmentIdDTO().getId();
            if (validator.validate(offerDTO).isEmpty() &&
            agentRepository.existsByFirstName(firstName)) {
                Offer offer = new Offer();
                offer.setAgent(agentRepository.getByFirstName(firstName));
                offer.setApartment(apartmentRepository.getById(id));
                offer.setPrice(offerDTO.getPrice());
                offer.setPublishedOn(toLocalDate(offerDTO.getPublishedOn()));
                result.add(String.format("Successfully imported offer %.2f",
                        offerDTO.getPrice().doubleValue()));
                offerRepository.save(offer);
            } else {
                result.add("Invalid offer");
            }
        }

        return String.join("\n", result);
    }

    @Override
    public String exportOffers() {
        List<String> collect = offerRepository.exportOffers()
                .stream()
                .map(OfferExportDTO::toString)
                .toList();
        return String.join("\n", collect);
    }
}
