package softuni.exam.models.dto;

import java.math.BigDecimal;

public class OfferExportDTO {
    String firstName;
    String lastName;
    Long offerId;
    Double apartmentArea;
    String townName;
    BigDecimal price;

    public OfferExportDTO(String firstName,
                          String lastName,
                          Long offerId,
                          Double apartmentArea,
                          String townName,
                          BigDecimal price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offerId = offerId;
        this.apartmentArea = apartmentArea;
        this.townName = townName;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Agent %s %s with offer №%d:%n-Apartment area: %.2f%n--Town: %s%n---Price: %.2f$",
                firstName,
                lastName,
                offerId,
                apartmentArea,
                townName,
                price.doubleValue());
    }

    public String getFirstName() {
        return firstName;
    }

    public OfferExportDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OfferExportDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getOfferId() {
        return offerId;
    }

    public OfferExportDTO setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public Double getApartmentArea() {
        return apartmentArea;
    }

    public OfferExportDTO setApartmentArea(Double apartmentArea) {
        this.apartmentArea = apartmentArea;
        return this;
    }

    public String getTownName() {
        return townName;
    }

    public OfferExportDTO setTownName(String townName) {
        this.townName = townName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferExportDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}