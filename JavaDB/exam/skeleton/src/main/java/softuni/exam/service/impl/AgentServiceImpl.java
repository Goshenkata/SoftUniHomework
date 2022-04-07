package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {
    Gson gson;
    Validator validator;
    ModelMapper mapper;
    TownRepository townRepository;
    AgentRepository agentRepository;

    public AgentServiceImpl(Gson gson,
                            Validator validator,
                            ModelMapper mapper,
                            TownRepository townRepository,
                            AgentRepository agentRepository) {
        this.gson = gson;
        this.validator = validator;
        this.mapper = mapper;
        this.townRepository = townRepository;
        this.agentRepository = agentRepository;
    }

    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "agents.json");
        return Files.readString(path);
    }

    @Override
    public String importAgents() throws IOException {
        List<String> result = new ArrayList<>();
        String json = readAgentsFromFile();
        AgentDTO[] agentDTOS = gson.fromJson(json, AgentDTO[].class);
        for (AgentDTO agentDTO : agentDTOS) {
            if (validator.validate(agentDTO).isEmpty() &&
            !(agentRepository.existsByFirstName(agentDTO.getFirstName())) &&
            townRepository.existsByTownName(agentDTO.getTown()) &&
            !(agentRepository.existsByEmail(agentDTO.getEmail()))) {
                Agent agent = mapper.map(agentDTO, Agent.class);
                agent.setTown(townRepository.getByTownName(agentDTO.getTown()));
                result.add(String.format("Successfully imported agent - %s %s",
                        agentDTO.getFirstName(),
                        agentDTO.getLastName()));
                agentRepository.save(agent);
            } else {
                result.add("Invalid agent");
            }
        }
        return String.join("\n", result);
    }
}
