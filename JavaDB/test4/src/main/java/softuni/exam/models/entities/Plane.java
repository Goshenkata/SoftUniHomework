package softuni.exam.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "planes")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String airline;
    @Column(nullable = false)
    Integer capacity;
    @Column(unique = true, nullable = false)
    String registerNumber;
    @OneToMany(mappedBy = "plane")
    List<Ticket> tickets;

    public Plane() {
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Plane setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Plane setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAirline() {
        return airline;
    }

    public Plane setAirline(String airline) {
        this.airline = airline;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Plane setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public Plane setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
        return this;
    }
}