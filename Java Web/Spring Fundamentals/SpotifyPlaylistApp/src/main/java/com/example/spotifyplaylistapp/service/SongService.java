package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.PlaylistSongInfoDTO;
import com.example.spotifyplaylistapp.model.dto.SongAddDTO;
import com.example.spotifyplaylistapp.model.dto.SongInfoDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.StyleName;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.user.CurrentUser;
import com.example.spotifyplaylistapp.util.TimeConversionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SongService {
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;
    private final SongRepository songRepository;
    private final ModelMapper mapper;

    public boolean performerNotUnique(String performer) {
        return songRepository.existsByPerformer(performer);
    }

    public void add(SongAddDTO songAddDTO) {
        Song song = mapper.map(songAddDTO, Song.class);
        song.setStyle(styleRepository.findByName(songAddDTO.getStyleName()));
        songRepository.save(song);
    }

    public List<SongInfoDTO> getPopSongInfo() {
        return songRepository.getSongInfoByStyle(StyleName.POP);
    }

    public List<SongInfoDTO> getRockSongInfo() {
        return songRepository.getSongInfoByStyle(StyleName.ROCK);
    }

    public List<SongInfoDTO> getJazzSongInfo() {
        return songRepository.getSongInfoByStyle(StyleName.JAZZ);
    }

    @Transactional
    public void addToPlaylist(Long id, String username) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("invalid song id"));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not logged in!"));
        if (!user.getPlaylist().contains(song)) {
            user.getPlaylist().add(song);
        } else {
            log.warn("song is already in playlist");
        }
    }

    public String getTotalDurationOfPlayList() {
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new RuntimeException("user is not logged in!"));
        int duration = songRepository.findAllByUsersContaining(user).stream().mapToInt(Song::getDuration).sum();
        return TimeConversionUtil.secondsToMinuteAndSeconds(duration);
    }

    public List<PlaylistSongInfoDTO> getPlaylist() {
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new RuntimeException("user is not logged in!"));
        return  songRepository.findAllByUsersContaining(user)
                .stream().map(song -> new PlaylistSongInfoDTO(song.getPerformer(), song.getTitle(), song.getDuration()))
                .toList();
    }

    @Transactional
    public void deletePlaylist() {
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new RuntimeException("user is not logged in!"));
        user.getPlaylist().clear();
        userRepository.save(user);
    }
}
