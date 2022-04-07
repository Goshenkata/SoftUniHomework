package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false, unique = true)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @ManyToOne
    Town town;
    @OneToMany(mappedBy = "agent")
    List<Offer> offers;

    public Agent() {
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public Agent setOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Agent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Agent setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Agent setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Agent setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Agent setTown(Town town) {
        this.town = town;
        return this;
    }
}