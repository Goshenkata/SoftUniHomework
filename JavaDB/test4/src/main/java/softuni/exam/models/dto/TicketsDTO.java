package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketsDTO implements Serializable {
    @XmlElement(name = "ticket")
    List<TicketDTO> tickets;

    public TicketsDTO() {
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public TicketsDTO setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
        return this;
    }
}