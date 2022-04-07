package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferDTO implements Serializable {
    @XmlElement(name = "price")
    @Positive
    BigDecimal price;
    @XmlElement(name = "agent")
    AgentNameDTO agentNameDTO;
    @XmlElement(name = "apartment")
    ApartmentIdDTO apartmentIdDTO;
    String publishedOn;

    public OfferDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public AgentNameDTO getAgentNameDTO() {
        return agentNameDTO;
    }

    public OfferDTO setAgentNameDTO(AgentNameDTO agentNameDTO) {
        this.agentNameDTO = agentNameDTO;
        return this;
    }

    public ApartmentIdDTO getApartmentIdDTO() {
        return apartmentIdDTO;
    }

    public OfferDTO setApartmentIdDTO(ApartmentIdDTO apartmentIdDTO) {
        this.apartmentIdDTO = apartmentIdDTO;
        return this;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public OfferDTO setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
        return this;
    }
}