package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.Picture;

import java.util.List;

//ToDo
@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    boolean existsByPath(String path);

    Picture getByPath(String profilePicture);

    List<Picture> findAllBySizeGreaterThanOrderBySizeAsc(Double size);
}
