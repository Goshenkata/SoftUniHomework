package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersDTO implements Serializable {
    @XmlElement(name = "offer")
    List<OfferDTO> offers;

    public OffersDTO() {
    }

    public List<OfferDTO> getOffers() {
        return offers;
    }

    public OffersDTO setOffers(List<OfferDTO> offers) {
        this.offers = offers;
        return this;
    }
}