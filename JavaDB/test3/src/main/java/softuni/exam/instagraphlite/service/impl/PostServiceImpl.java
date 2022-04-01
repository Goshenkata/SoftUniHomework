package softuni.exam.instagraphlite.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostDTO;
import softuni.exam.instagraphlite.models.dto.PostsDTO;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    UserRepository userRepository;
    PostRepository postRepository;
    PictureRepository pictureRepository;
    Validator validator;

    public PostServiceImpl(UserRepository userRepository,
                           PostRepository postRepository,
                           PictureRepository pictureRepository,
                           Validator validator) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.pictureRepository = pictureRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "posts.xml");
        return Files.readString(path);
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        List<String> result = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(PostsDTO.class);
        StringReader stringReader = new StringReader(readFromFileContent());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PostsDTO postsDTO = (PostsDTO) unmarshaller.unmarshal(stringReader);
        for (PostDTO postDTO : postsDTO.getPosts()) {
            Set<ConstraintViolation<PostDTO>> validate = validator.validate(postDTO);
            String path = postDTO.getPath().getPath();
            String username = postDTO.getUser().getUsername();
            if (validate.isEmpty() &&
                pictureRepository.existsByPath(path) &&
                userRepository.existsByUsername(username)) {
                Post post = new Post();
                post.setCaption(postDTO.getCaption());
                post.setPicture(pictureRepository.getByPath(path));
                post.setUser(userRepository.getByUsername(username));
                result.add(String.format("Successfully imported Post, made by %s", username));
                postRepository.save(post);
            } else {
                result.add("Invalid Post");
            }
        }
        return String.join("\n",result);
    }
}
