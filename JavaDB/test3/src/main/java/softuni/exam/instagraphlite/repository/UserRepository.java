package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.instagraphlite.models.entity.User;

import java.util.List;

//ToDo
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    @Query("select u FROM User u order by u.posts.size desc, u.id")
    List<User> findAllByPostCount();
    User getByUsername(String username);
}
