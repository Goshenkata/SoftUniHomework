package com.example.exam5.init;

import com.example.exam5.model.entity.Classification;
import com.example.exam5.model.entity.ClassificationName;
import com.example.exam5.repository.ClassificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DbInit implements CommandLineRunner {
    private final ClassificationRepository classificationRepository;

    @Override
    public void run(String... args) throws Exception {
        if (classificationRepository.count() == 0){
        List<Classification> classifications = Arrays.stream(ClassificationName.values())
                .map(c -> {
                    Classification classification = new Classification();
                    classification.setClassificationName(c);
                    return classification;
                })
                .toList();
        classificationRepository.saveAll(classifications);
        }
    }
}
