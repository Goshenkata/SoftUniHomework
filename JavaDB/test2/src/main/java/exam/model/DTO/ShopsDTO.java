package exam.model.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopsDTO {
    @XmlElement(name = "shop")
    List<ShopDTO> shop;

    public ShopsDTO() {
    }

    public List<ShopDTO> getShops() {
        return shop;
    }

    public ShopsDTO setShop(List<ShopDTO> shop) {
        this.shop = shop;
        return this;
    }
}
