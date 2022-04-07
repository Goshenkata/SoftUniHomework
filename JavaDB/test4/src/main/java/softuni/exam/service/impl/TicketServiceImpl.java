package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TicketDTO;
import softuni.exam.models.dto.TicketsDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Ticket;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    ModelMapper mapper;
    Validator validator;
    TicketRepository ticketRepository;
    PlaneRepository planeRepository;
    PassengerRepository passengerRepository;
    TownRepository townRepository;

    public TicketServiceImpl(ModelMapper mapper,
                             Validator validator,
                             TicketRepository ticketRepository,
                             PlaneRepository planeRepository,
                             PassengerRepository passengerRepository,
                             TownRepository townRepository) {
        this.mapper = mapper;
        this.validator = validator;
        this.ticketRepository = ticketRepository;
        this.planeRepository = planeRepository;
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "tickets.xml");
        return Files.readString(path);
    }

    @Override
    public String importTickets() throws JAXBException, IOException {
        List<String> result = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(TicketsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader stringReader = new StringReader(readTicketsFileContent());
        TicketsDTO ticketsDTO = (TicketsDTO) unmarshaller.unmarshal(stringReader);
        for (TicketDTO ticketDTO : ticketsDTO.getTickets()) {
            String fromTownName = ticketDTO.getFromTown().getName();
            String toTownName = ticketDTO.getToTown().getName();
            String passangerEmail = ticketDTO.getPassenger().getEmail();
            String planeRegisterNumber = ticketDTO.getPlaneNumberDTO().getRegisterNumber();
            if (validator.validate(ticketDTO).isEmpty() &&
                    townRepository.existsByName(fromTownName) &&
                    townRepository.existsByName(toTownName) &&
                    passengerRepository.existsByEmail(passangerEmail) &&
                    planeRepository.existsByRegisterNumber(planeRegisterNumber) &&
                    !(ticketRepository.existsBySerialNumber(ticketDTO.getSerialNumber()))) {
                Ticket ticket = mapper.map(ticketDTO, Ticket.class);
                ticket.setFromTown(townRepository.getByName(fromTownName));
                ticket.setToTown(townRepository.getByName(toTownName));
                ticket.setPassenger(passengerRepository.getByEmail(passangerEmail));
                ticket.setPlane(planeRepository.getByRegisterNumber(planeRegisterNumber));
                ticket.setTakeOff(toLocalDateTime(ticketDTO.getTakeOff()));
                result.add(String.format("Successfully imported ticket %s - %s", fromTownName, toTownName));
                ticketRepository.save(ticket);
            } else {
                result.add("Invalid Ticket");
            }
        }

        return String.join("\n", result);
    }

    private LocalDateTime toLocalDateTime(String takeOff) {
        String date = takeOff.split(" ")[0];
        String time = takeOff.split(" ")[1];
        return LocalDateTime.of(toDate(date), toTime(time));
    }

    private LocalTime toTime(String time) {
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int min = Integer.parseInt(timeParts[1]);
        int sec = Integer.parseInt(timeParts[2]);
        return LocalTime.of(hour,min,sec);
    }

    private LocalDate toDate(String date) {
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[1]);
        return LocalDate.of(year, month, day);
    }

}