package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserDTO;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    Gson gson;
    ModelMapper mapper;
    Validator validator;
    UserRepository userRepository;
    PictureRepository pictureRepository;

    public UserServiceImpl(Gson gson,
                           ModelMapper mapper,
                           Validator validator,
                           UserRepository userRepository,
                           PictureRepository pictureRepository) {
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "users.json");
        return Files.readString(path);
    }

    @Override
    public String importUsers() throws IOException {
        List<String> result = new ArrayList<>();
        String json = readFromFileContent();
        UserDTO[] userDTOS = gson.fromJson(json, UserDTO[].class);
        for (UserDTO userDTO : userDTOS) {
            Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO);
            if (validate.isEmpty() &&
                    !(userRepository.existsByUsername(userDTO.getUsername())) &&
                    pictureRepository.existsByPath(userDTO.getProfilePicture())) {
                User user = mapper.map(userDTO, User.class);
                Picture picture = pictureRepository.getByPath(userDTO.getProfilePicture());
                user.setProfilePicture(picture);
                result.add(String.format("Successfully imported User: %s",
                        user.getUsername()));
                userRepository.save(user);
            } else {
                result.add("Invalid User");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String exportUsersWithTheirPosts() {
        List<String> result = new ArrayList<>();
        List<User> users = userRepository.findAllByPostCount();
        for (User user : users) {
            StringBuilder format = new StringBuilder(String.format("User: %s%n" +
                    "Post count: %d%n", user.getUsername(), user.getPosts().size()));
            List<Post> posts = user.getPosts();
            posts.sort(Comparator.comparingDouble(post -> post.getPicture().getSize()));
            for (Post post : posts) {
                format.append(String.format("==Post Details:%n" +
                        "----Caption: %s%n" +
                        "----Picture Size: %.2f%n", post.getCaption(), post.getPicture().getSize()));
            }
            result.add(format.toString());
        }
        return String.join("\n", result);
    }
}
