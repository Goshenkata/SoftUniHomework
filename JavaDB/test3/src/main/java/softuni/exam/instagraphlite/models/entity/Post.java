package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String caption;
    @ManyToOne
    User user;
    @ManyToOne
    Picture picture;

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public Post setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Post setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Post setUser(User user) {
        this.user = user;
        return this;
    }

    public Picture getPicture() {
        return picture;
    }

    public Post setPicture(Picture picture) {
        this.picture = picture;
        return this;
    }
}