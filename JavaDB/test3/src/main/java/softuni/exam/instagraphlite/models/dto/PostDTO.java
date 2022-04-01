package softuni.exam.instagraphlite.models.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostDTO implements Serializable {
    @Size(min = 21)
    @NotNull
    @XmlElement(name = "caption")
    String caption;
    @NotNull
    @XmlElement(name = "user")
    UserNameDTO user;
    @NotNull
    @XmlElement(name = "picture")
    UserPathDTO path;

    public PostDTO() {
    }

    public String getCaption() {
        return caption;
    }

    public PostDTO setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public UserNameDTO getUser() {
        return user;
    }

    public PostDTO setUser(UserNameDTO user) {
        this.user = user;
        return this;
    }

    public UserPathDTO getPath() {
        return path;
    }

    public PostDTO setPath(UserPathDTO path) {
        this.path = path;
        return this;
    }
}