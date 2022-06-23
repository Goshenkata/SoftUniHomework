package com.example.exam4.service;

import com.example.exam4.model.entity.Artist;
import com.example.exam4.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    public List<String> getAllNames() {
        return artistRepository.findAll()
                .stream()
                .map(Artist::getName)
                .toList();
    }

    public Artist getByName(String artistName) {
        return artistRepository
                .findByName(artistName)
                .orElseThrow(() -> new RuntimeException("Artist: " + artistName + " not found"));
    }
}
