package softuni.exam.instagraphlite.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsDTO implements Serializable {
    @XmlElement(name = "post")
    List<PostDTO> posts;

    public PostsDTO() {
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public PostsDTO setPosts(List<PostDTO> posts) {
        this.posts = posts;
        return this;
    }
}
