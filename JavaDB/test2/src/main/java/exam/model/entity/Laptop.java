package exam.model.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Entity(name = "laptops")
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Double cpuSpeed;
    @Column(nullable = false, columnDefinition = "text")
    String description;
    @Column(nullable = false, unique = true)
    String macAddress;
    @Column(nullable = false)
    BigDecimal price;
    @Column(nullable = false)
    Integer ram;
    @Column(nullable = false)
    Integer storage;
    @Enumerated
    WarrantyType warrantyType;
    @ManyToOne
    Shop shop;

    public Laptop() {
    }

    public Long getId() {
        return id;
    }

    public Laptop setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getCpuSpeed() {
        return cpuSpeed;
    }

    public Laptop setCpuSpeed(Double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Laptop setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public Laptop setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Laptop setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getRam() {
        return ram;
    }

    public Laptop setRam(Integer ram) {
        this.ram = ram;
        return this;
    }

    public Integer getStorage() {
        return storage;
    }

    public Laptop setStorage(Integer storage) {
        this.storage = storage;
        return this;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public Laptop setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public Laptop setShop(Shop shop) {
        this.shop = shop;
        return this;
    }
}