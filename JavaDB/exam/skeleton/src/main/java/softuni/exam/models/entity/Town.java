package softuni.exam.models.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Integer population;
    @Column(nullable = false, unique = true)
    String townName;
    @OneToMany(mappedBy = "town")
    List<Agent> agents;
    @OneToMany(mappedBy = "town")
    List<Apartment> apartments;

    public Town() {
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public Town setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
        return this;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public Town setAgents(List<Agent> agents) {
        this.agents = agents;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Town setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public Town setPopulation(Integer population) {
        this.population = population;
        return this;
    }

    public String getTownName() {
        return townName;
    }

    public Town setTownName(String townName) {
        this.townName = townName;
        return this;
    }
}