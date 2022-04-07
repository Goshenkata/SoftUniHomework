package softuni.exam.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    BigDecimal price;
    @Column
    String serialNumber;
    @Column
    LocalDateTime takeOff;
    @ManyToOne
    Town fromTown;
    @ManyToOne
    Town toTown;
    @ManyToOne
    Plane plane;
    @ManyToOne
    Passenger passenger;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public Ticket setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Ticket setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Ticket setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public LocalDateTime getTakeOff() {
        return takeOff;
    }

    public Ticket setTakeOff(LocalDateTime takeOff) {
        this.takeOff = takeOff;
        return this;
    }

    public Town getFromTown() {
        return fromTown;
    }

    public Ticket setFromTown(Town fromTown) {
        this.fromTown = fromTown;
        return this;
    }

    public Town getToTown() {
        return toTown;
    }

    public Ticket setToTown(Town toTown) {
        this.toTown = toTown;
        return this;
    }

    public Plane getPlane() {
        return plane;
    }

    public Ticket setPlane(Plane plane) {
        this.plane = plane;
        return this;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Ticket setPassenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }
}