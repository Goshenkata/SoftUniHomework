package com.example.automapping.service;

import com.example.automapping.config.GameDTO;
import com.example.automapping.entities.Games;
import com.example.automapping.repository.GamesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GamesService {

    GamesRepository gamesRepository;
    ModelMapper modelMapper;

    public GamesService(GamesRepository gamesRepository, ModelMapper modelMapper) {
        this.gamesRepository = gamesRepository;
        this.modelMapper = modelMapper;
    }

    public void save(GameDTO gameDTO) {
        gamesRepository.save(modelMapper.map(gameDTO, Games.class));
    }
}
