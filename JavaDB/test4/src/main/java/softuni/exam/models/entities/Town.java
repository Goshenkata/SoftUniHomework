package softuni.exam.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String guide;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    Integer population;
    @OneToMany(mappedBy = "town")
    List<Passenger> passengers;
    @OneToMany(mappedBy = "fromTown")
    List<Ticket> fromTickets;
    @OneToMany(mappedBy = "toTown")
    List<Ticket> toTickets;

    public Town() {
    }

    public List<Ticket> getFromTickets() {
        return fromTickets;
    }

    public Town setFromTickets(List<Ticket> fromTickets) {
        this.fromTickets = fromTickets;
        return this;
    }

    public List<Ticket> getToTickets() {
        return toTickets;
    }

    public Town setToTickets(List<Ticket> toTickets) {
        this.toTickets = toTickets;
        return this;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Town setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Town setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGuide() {
        return guide;
    }

    public Town setGuide(String guide) {
        this.guide = guide;
        return this;
    }

    public String getName() {
        return name;
    }

    public Town setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public Town setPopulation(Integer population) {
        this.population = population;
        return this;
    }
}