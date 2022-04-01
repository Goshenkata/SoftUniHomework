package exam.model.DTO;

import antlr.Grammar;

import java.io.Serializable;
import java.math.BigDecimal;

public class BestLaptopDTO implements Serializable {
    String macAddress;
    Double cpuSpeed;
    Integer ram;
    Integer storage;
    BigDecimal price;
    String shop;
    String town;

    public BestLaptopDTO(String macAddress,
                         Double cpuSpeed,
                         Integer ram,
                         Integer storage,
                         BigDecimal price,
                         String shop,
                         String town) {
        this.macAddress = macAddress;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.price = price;
        this.shop = shop;
        this.town = town;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public BestLaptopDTO setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public Double getCpuSpeed() {
        return cpuSpeed;
    }

    public BestLaptopDTO setCpuSpeed(Double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        return this;
    }

    public Integer getRam() {
        return ram;
    }

    public BestLaptopDTO setRam(Integer ram) {
        this.ram = ram;
        return this;
    }

    public Integer getStorage() {
        return storage;
    }

    public BestLaptopDTO setStorage(Integer storage) {
        this.storage = storage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BestLaptopDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getShop() {
        return shop;
    }

    public BestLaptopDTO setShop(String shop) {
        this.shop = shop;
        return this;
    }

    public String getTown() {
        return town;
    }

    public BestLaptopDTO setTown(String town) {
        this.town = town;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Laptop - %s%n" +
                "*Cpu speed - %.2f%n" +
                "**Ram - %d%n" +
                "***Storage - %s%n" +
                "****Price - %.2f%n" +
                "#Shop name - %s%n" +
                "##Town - %s%n",
                macAddress,
                cpuSpeed,
                ram,
                storage,
                price.doubleValue(),
                shop,
                town);
    }
}