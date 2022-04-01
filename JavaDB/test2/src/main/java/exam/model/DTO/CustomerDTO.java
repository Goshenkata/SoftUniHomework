package exam.model.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CustomerDTO implements Serializable {
    @Size(min = 2)
    String firstName;
    @Size(min = 2)
    String lastName;
    @Email
    String email;
    String registeredOn;
    TownDTOJson town;

    public CustomerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public CustomerDTO setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    public TownDTOJson getTown() {
        return town;
    }

    public CustomerDTO setTown(TownDTOJson town) {
        this.town = town;
        return this;
    }
}