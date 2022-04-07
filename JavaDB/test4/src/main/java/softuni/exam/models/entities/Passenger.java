package softuni.exam.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Integer age;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String phoneNumber;
    @ManyToOne
    Town town;
    @OneToMany(mappedBy = "passenger")
    List<Ticket> tickets;

    public Passenger() {
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Passenger setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Passenger setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Passenger setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Passenger setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Passenger setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Passenger setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Passenger setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Passenger setTown(Town town) {
        this.town = town;
        return this;
    }
}