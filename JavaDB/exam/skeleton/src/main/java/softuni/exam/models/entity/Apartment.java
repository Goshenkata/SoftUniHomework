package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    ApartmentType apartmentType;
    @Column(nullable = false)
    Double area;
    @ManyToOne()
    Town town;
    @OneToMany(mappedBy = "apartment")
    List<Offer> offers;

    public Apartment() {
    }

    public Long getId() {
        return id;
    }

    public Apartment setId(Long id) {
        this.id = id;
        return this;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public Apartment setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public Apartment setArea(Double area) {
        this.area = area;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Apartment setTown(Town town) {
        this.town = town;
        return this;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public Apartment setOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }
}