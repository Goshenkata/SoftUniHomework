package exam.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "customers")
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    LocalDate registeredOn;
    @ManyToOne
    Town town;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public Customer setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Customer setTown(Town town) {
        this.town = town;
        return this;
    }

}