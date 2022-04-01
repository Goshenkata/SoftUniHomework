package softuni.exam.instagraphlite.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserNameDTO implements Serializable {
    @XmlElement(name = "username")
    String username;

    public UserNameDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserNameDTO setUsername(String username) {
        this.username = username;
        return this;
    }

}