package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleName;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DBInit implements CommandLineRunner {
    private final StyleRepository styleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (styleRepository.count() == 0) {
            List<Style> styles = Arrays.stream(StyleName.values())
                    .map(Style::new)
                    .toList();
            styleRepository.saveAll(styles);
        }
    }
}
