package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PassengerDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    ModelMapper mapper;
    Gson gson;
    Validator validator;
    PassengerRepository passengerRepository;
    TownRepository townRepository;

    public PassengerServiceImpl(ModelMapper mapper,
                                Gson gson,
                                PassengerRepository passengerRepository,
                                TownRepository townRepository,
                                Validator validator) {
        this.mapper = mapper;
        this.gson = gson;
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "passengers.json");
        return Files.readString(path);
    }

    @Override
    public String importPassengers() throws IOException {
        List<String> result = new ArrayList<>();
        String json = readPassengersFileContent();
        for (PassengerDTO passengerDTO : gson.fromJson(json, PassengerDTO[].class)) {
            if (validator.validate(passengerDTO).isEmpty() &&
                    !(passengerRepository.existsByEmail(passengerDTO.getEmail())) &&
                    townRepository.existsByName(passengerDTO.getTown())) {
                Passenger map = mapper.map(passengerDTO, Passenger.class);
                map.setTickets(new ArrayList<>());
                map.setTown(townRepository.getByName(passengerDTO.getTown()));
                result.add(String.format("Successfully imported Passenger %s - %s",
                        passengerDTO.getLastName(),
                        passengerDTO.getEmail()));
                passengerRepository.save(map);
            } else {
                result.add("Invalid Passenger");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        List<String> result = new ArrayList<>();
        for (Passenger passenger : passengerRepository.findAllByOrderByTicketsCountThenByEmail()) {
            result.add(String.format("Passenger %s %s " +
                            "Email - %s " +
                    "Phone - {phoneNumber} " +
                    "Number of tickets - %d",
                    passenger.getFirstName(), passenger.getLastName(),
                    passenger.getEmail(), passenger.getPhoneNumber(),
                    passenger.getTickets().size()));
        }

        return null;
    }
}
