package com.example.exam4.service;

import com.example.exam4.currentUser.CurrentUser;
import com.example.exam4.model.dto.AlbumAddDTO;
import com.example.exam4.model.dto.AlbumStatDTO;
import com.example.exam4.model.entity.Album;
import com.example.exam4.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final ArtistService artistService;
    private final ModelMapper mapper;

    public void add(AlbumAddDTO albumAddDTO) {
        Album album = mapper.map(albumAddDTO, Album.class);
        album.setAddedFrom(userService.getCurrentUser());
        album.setArtist(artistService.getByName(albumAddDTO.getArtistName()));
        albumRepository.save(album);
    }

    public List<AlbumStatDTO> getAllAlbums() {
        return albumRepository.findAll()
                .stream()
                .map(a -> mapper.map(a, AlbumStatDTO.class))
                .toList();
    }

    public Long getTotalCopies() {
        Long totalCopies = albumRepository.getTotalCopies();
        return totalCopies == null ? 0 : totalCopies;
    }

    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
}