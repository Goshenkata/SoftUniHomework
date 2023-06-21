package christmasPastryShop.entities.drinks;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.entities.drinks.interfaces.Cocktail;

public abstract class BaseCocktail implements Cocktail {
    private String name;

    private int size;

    private double price;

    private String brand;

    protected BaseCocktail(String name, int size, double price, String brand) {
        this.setName(name);
        this.setSize(size);
        this.setPrice(price);
        this.setBrand(brand);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SIZE);
        }
        this.size = size;
    }

    @Override
    public double getPrice() {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.brand = brand;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s - %dml - %.2flv",
                name, brand, size, price
        );
    }
}
