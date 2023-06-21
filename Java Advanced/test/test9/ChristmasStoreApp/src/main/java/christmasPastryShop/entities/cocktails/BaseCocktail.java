package christmasPastryShop.entities.cocktails;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;

public abstract class BaseCocktail implements Cocktail {
    private String name;
    private int size;
    private double price;
    private String brand;

    public BaseCocktail(String name, int size, double price, String brand) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        if (size <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SIZE);
        }
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.name = name;
        this.size = size;
        this.price = price;
        this.brand = brand;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %dml - %.2flv", name, brand, size, price);
    }
}
