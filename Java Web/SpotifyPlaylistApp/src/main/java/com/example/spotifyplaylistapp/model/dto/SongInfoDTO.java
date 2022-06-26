package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.util.TimeConversionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongInfoDTO {
    private Long id;
    private String performer;
    private String title;
    private Integer duration;
    private String durationInMinutes;

    public SongInfoDTO(Long id, String performer, String title, Integer duration) {
        this.id = id;
        this.performer = performer;
        this.title = title;
        this.duration = duration;
        this.durationInMinutes=String.format("(%s min)", TimeConversionUtil.secondsToMinuteAndSeconds(duration));
    }
}
