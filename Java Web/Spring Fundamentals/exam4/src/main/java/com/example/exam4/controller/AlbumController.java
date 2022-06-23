package com.example.exam4.controller;

import com.example.exam4.currentUser.CurrentUser;
import com.example.exam4.model.dto.AlbumAddDTO;
import com.example.exam4.model.dto.AlbumStatDTO;
import com.example.exam4.model.entity.Genre;
import com.example.exam4.service.AlbumService;
import com.example.exam4.service.ArtistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
public class AlbumController {
    private final CurrentUser currentUser;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @GetMapping("/add")
    public String add(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        model.addAttribute("genres", Genre.values());
        model.addAttribute("artists", artistService.getAllNames());
        return "add-album";
    }

    @ModelAttribute("albumAddDTO")
    public AlbumAddDTO albumAddDTO() {
        return new AlbumAddDTO();
    }

    @PostMapping("/add")
    public String post(@Valid AlbumAddDTO albumAddDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddDTO", albumAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddDTO", bindingResult);
            log.warn("invalid data!");
            return "redirect:/add";
        }
        albumService.add(albumAddDTO);
        log.info("Album added successfully");
        return "redirect:/";
    }

    @PostMapping("/album/{id}")
    public String delete(@PathVariable("id") Long id) {
        albumService.delete(id);
        log.info("Album with id " + id + " deleted");
        return "redirect:/";
    }
}
