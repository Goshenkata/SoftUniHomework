package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, unique = true)
    String path;
    @Column(nullable = false)
    Double size;
    @OneToMany(mappedBy = "profilePicture")
    List<User> users;
    @OneToMany(mappedBy = "picture")
    List<Post> posts;

    public Picture() {
    }

    public Integer getId() {
        return id;
    }

    public Picture setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Picture setPath(String path) {
        this.path = path;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public Picture setSize(Double size) {
        this.size = size;
        return this;
    }
}