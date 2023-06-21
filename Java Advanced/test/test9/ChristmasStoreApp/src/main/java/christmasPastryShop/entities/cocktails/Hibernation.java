package christmasPastryShop.entities.cocktails;

import christmasPastryShop.entities.cocktails.BaseCocktail;

public class Hibernation extends BaseCocktail {
    private static final double hibernationPrice = 4.5;

    public Hibernation(String name, int size, String brand) {
        super(name, size, hibernationPrice, brand);
    }
}
