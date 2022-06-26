package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.util.TimeConversionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PlaylistSongInfoDTO {
    private String performer;
    private String title;
    private String durationInMinutes;
    private Integer duration;

    public PlaylistSongInfoDTO(String performer, String title, Integer duration) {
        this.performer = performer;
        this.title = title;
        this.durationInMinutes = TimeConversionUtil.secondsToMinuteAndSeconds(duration);
    }
}