package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureDTO;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    ModelMapper mapper;
    Gson gson;
    PictureRepository pictureRepository;
    Validator validator;

    public PictureServiceImpl(ModelMapper mapper,
                              Gson gson,
                              PictureRepository pictureRepository,
                              Validator validator) {
        this.mapper = mapper;
        this.gson = gson;
        this.pictureRepository = pictureRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "pictures.json");
        return Files.readString(path);
    }

    @Override
    public String importPictures() throws IOException {
        List<String> result = new ArrayList<>();
        String json = readFromFileContent();
        PictureDTO[] pictureDTOS = gson.fromJson(json, PictureDTO[].class);
        for (PictureDTO pictureDTO : pictureDTOS) {
            Set<ConstraintViolation<PictureDTO>> validate = validator.validate(pictureDTO);
            if (validate.isEmpty() && !(pictureRepository.existsByPath(pictureDTO.getPath()))) {
                Picture map = mapper.map(pictureDTO, Picture.class);
                String imp = String.format("Successfully imported Picture, with size %.2f", pictureDTO.getSize());
                result.add(imp);
                pictureRepository.save(map);
            } else {
                result.add("Invalid Picture");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String exportPictures() {
        List<Picture> allOrderBySize = pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(30000.0);
        List<String> result = allOrderBySize
                .stream().map(p -> String.format("%.2f - %s", p.getSize(), p.getPath())).toList();
        return String.join("\n", result);
    }
}
