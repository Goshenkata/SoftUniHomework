package exam.model.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "shops")
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    BigDecimal income;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    Integer shopArea;
    @ManyToOne
    Town town;
    @OneToMany(mappedBy = "shop")
    List<Laptop> laptops;

    public Shop() {
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public Shop setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Shop setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public Shop setIncome(BigDecimal income) {
        this.income = income;
        return this;
    }

    public String getName() {
        return name;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getShopArea() {
        return shopArea;
    }

    public Shop setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Shop setTown(Town town) {
        this.town = town;
        return this;
    }
}