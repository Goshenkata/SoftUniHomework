package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.SongInfoDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private final CurrentUser currentUser;
    private final SongService songService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        if (currentUser.isLoggedIn()) {
            model.addAttribute("totalDuration", songService.getTotalDurationOfPlayList());
            model.addAttribute("jazzSongs", songService.getJazzSongInfo());
            model.addAttribute("popSongs", songService.getPopSongInfo());
            model.addAttribute("rockSongs", songService.getRockSongInfo());
            model.addAttribute("playlist", songService.getPlaylist());
            return "home";
        } else {
            return "index";
        }
    }


}