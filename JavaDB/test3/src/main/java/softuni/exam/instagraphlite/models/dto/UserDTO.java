package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserDTO implements Serializable {
    @NotNull
    @Size(min = 2, max = 18)
    String username;
    @NotNull
    @Size(min = 4)
    String password;
    @NotNull
    String profilePicture;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserDTO setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}