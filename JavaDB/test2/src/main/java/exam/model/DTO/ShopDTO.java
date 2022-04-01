package exam.model.DTO;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopDTO implements Serializable {
    @XmlElement(name = "address")
    @Size(min = 4)
    String address;
    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    Integer employeeCount;
    @XmlElement(name = "shop-area")
    @Min(150)
    Integer shopArea;
    @XmlElement(name = "income")
    @Min(20000)
    BigDecimal income;
    @XmlElement(name = "name")
    @Size(min = 4)
    String name;
    @XmlElement(name = "town")
    TownNameDTO townName;

    public ShopDTO() {
    }

    public String getAddress() {
        return address;
    }

    public ShopDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public ShopDTO setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public ShopDTO setIncome(BigDecimal income) {
        this.income = income;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShopDTO setName(String name) {
        this.name = name;
        return this;
    }

    public TownNameDTO getTownName() {
        return townName;
    }

    public ShopDTO setTownName(TownNameDTO townName) {
        this.townName = townName;
        return this;
    }

    public Integer getShopArea() {
        return shopArea;
    }

    public ShopDTO setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
        return this;
    }
}