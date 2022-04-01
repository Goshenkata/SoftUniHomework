package exam.model.DTO;

import com.google.gson.annotations.SerializedName;
import exam.model.entity.WarrantyType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class LaptopDTO implements Serializable {
    @Size(min = 8)
    String macAddress;
    @Positive
    Double cpuSpeed;
    @Min(8)
    @Max(128)
    Integer ram;
    @Min(128)
    @Max(1024)
    Integer storage;
    @Size(min = 10)
    String description;
    @Positive
    BigDecimal price;
    String warrantyType;
    ShopDTOJSON shop;

    public LaptopDTO() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public LaptopDTO setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public Double getCpuSpeed() {
        return cpuSpeed;
    }

    public LaptopDTO setCpuSpeed(Double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        return this;
    }

    public Integer getRam() {
        return ram;
    }

    public LaptopDTO setRam(Integer ram) {
        this.ram = ram;
        return this;
    }

    public Integer getStorage() {
        return storage;
    }

    public LaptopDTO setStorage(Integer storage) {
        this.storage = storage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LaptopDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LaptopDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public LaptopDTO setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
        return this;
    }

    public ShopDTOJSON getShop() {
        return shop;
    }

    public LaptopDTO setShop(ShopDTOJSON shop) {
        this.shop = shop;
        return this;
    }
}