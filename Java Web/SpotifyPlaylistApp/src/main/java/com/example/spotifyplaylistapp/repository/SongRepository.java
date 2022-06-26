package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.dto.SongInfoDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleName;
import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    boolean existsByPerformer(String performer);
    @Query("select new com.example.spotifyplaylistapp.model.dto.SongInfoDTO(s.id, s.performer, s.title, s.duration) from Song s where s.style.name = ?1")
    List<SongInfoDTO> getSongInfoByStyle(StyleName style);
    List<Song> findAllByUsersContaining(User user);
}
