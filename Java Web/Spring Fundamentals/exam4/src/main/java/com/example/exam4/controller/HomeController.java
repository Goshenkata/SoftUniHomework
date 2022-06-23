package com.example.exam4.controller;

import com.example.exam4.currentUser.CurrentUser;
import com.example.exam4.model.entity.Genre;
import com.example.exam4.service.AlbumService;
import com.example.exam4.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    private final CurrentUser currentUser;
    private final AlbumService albumService;

    @GetMapping("/")
    public String home(Model model) {
        if (currentUser.isLoggedIn()) {
            model.addAttribute("albums", albumService.getAllAlbums());
            model.addAttribute("total", albumService.getTotalCopies());
            return "home";
        }
        return "index";
    }
}
