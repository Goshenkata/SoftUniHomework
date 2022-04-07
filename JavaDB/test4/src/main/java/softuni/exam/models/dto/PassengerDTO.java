package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PassengerDTO implements Serializable {
    @Size(min = 2)
    String firstName;
    @Size(min = 2)
    String lastName;
    @Positive
    Integer age;
    String phoneNumber;
    @Email
    String email;
    String town;

    public PassengerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public PassengerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PassengerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public PassengerDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PassengerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PassengerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public PassengerDTO setTown(String town) {
        this.town = town;
        return this;
    }
}