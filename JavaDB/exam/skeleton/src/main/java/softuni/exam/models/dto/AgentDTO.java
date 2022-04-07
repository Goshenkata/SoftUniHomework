package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AgentDTO implements Serializable {
    @Size(min = 2)
    String firstName;
    @Size(min = 2)
    String lastName;
    String town;
    @Email
    String email;

    public AgentDTO() {
    }

    public String getEmail() {
        return email;
    }

    public AgentDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AgentDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AgentDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getTown() {
        return town;
    }

    public AgentDTO setTown(String town) {
        this.town = town;
        return this;
    }
}
