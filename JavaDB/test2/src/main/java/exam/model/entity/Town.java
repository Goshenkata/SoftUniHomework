package exam.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "towns")
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    Integer population;
    @Column(nullable = false, columnDefinition = "text")
    String travelGuide;
    @OneToMany(mappedBy = "town")
    List<Shop> shops;
    @OneToMany(mappedBy = "town")
    List<Customer> customers;

    public Town() {
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Town setCustomers(List<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public Town setShops(List<Shop> shops) {
        this.shops = shops;
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

    public String getTravelGuide() {
        return travelGuide;
    }

    public Town setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}