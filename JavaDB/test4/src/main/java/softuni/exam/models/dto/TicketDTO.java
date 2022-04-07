package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketDTO {
    @XmlElement(name = "serial-number")
    @Size(min = 2)
    String serialNumber;
    @XmlElement(name = "price")
    @Positive
    BigDecimal price;
    @XmlElement(name = "take-off")
    String takeOff;
    @XmlElement(name = "from-town")
    FromTownDTO fromTown;
    @XmlElement(name = "to-town")
    ToTownDTO toTown;
    @XmlElement(name = "passenger")
    PassangerEmailDTO passenger;
    @XmlElement(name = "plane")
    PlaneNumberDTO planeNumberDTO;

    public TicketDTO() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public TicketDTO setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TicketDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public TicketDTO setTakeOff(String takeOff) {
        this.takeOff = takeOff;
        return this;
    }

    public FromTownDTO getFromTown() {
        return fromTown;
    }

    public TicketDTO setFromTown(FromTownDTO fromTown) {
        this.fromTown = fromTown;
        return this;
    }

    public ToTownDTO getToTown() {
        return toTown;
    }

    public TicketDTO setToTown(ToTownDTO toTown) {
        this.toTown = toTown;
        return this;
    }

    public PassangerEmailDTO getPassenger() {
        return passenger;
    }

    public TicketDTO setPassenger(PassangerEmailDTO passenger) {
        this.passenger = passenger;
        return this;
    }

    public PlaneNumberDTO getPlaneNumberDTO() {
        return planeNumberDTO;
    }

    public TicketDTO setPlaneNumberDTO(PlaneNumberDTO planeNumberDTO) {
        this.planeNumberDTO = planeNumberDTO;
        return this;
    }
}