package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.SongAddDTO;
import com.example.spotifyplaylistapp.model.entity.StyleName;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.user.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/song")
@AllArgsConstructor
@Slf4j
public class SongController {
    private final SongService songService;
    private final CurrentUser currentUser;


    @GetMapping("/add")
    public String addSong() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }
        return "song-add";
    }

    @ModelAttribute("styleNames")
    public List<StyleName> styleNameList() {
        return Arrays.stream(StyleName.values()).toList();
    }
    @ModelAttribute("songAddDTO")
    public SongAddDTO songAddDTO() {
       return new SongAddDTO();
    }

    @PostMapping("/add")
    public String addSong(@Valid SongAddDTO songAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("songAddDTO", songAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songAddDTO", bindingResult);
            log.warn("invalid input");
            return "redirect:/song/add";
        }
        if (songService.performerNotUnique(songAddDTO.getPerformer())) {
            redirectAttributes.addFlashAttribute("songAddDTO", songAddDTO);
            log.warn("performer is not unique");
            return "redirect:/song/add";
        }
        songService.add(songAddDTO);
        return "redirect:/";
    }

    @PostMapping("/playlist/add/{id}")
    public String addSongToPlaylist(@PathVariable("id") Long id) {
        songService.addToPlaylist(id, currentUser.getUsername());
        return "redirect:/";
    }


    @PostMapping("/playlist/delete")
    public String deletePlaylist() {
        songService.deletePlaylist();
        log.info("removed playlist");
        return "redirect:/";
    }

}