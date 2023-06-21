package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.drinks.interfaces.Cocktail;

public interface Booth {
    int getBoothNumber();

    int getCapacity();

    boolean isReserved();

    double getPrice();

    void reserve(int numberOfPeople);

    void orderDelicacy(Delicacy food);

    void orderCocktail(Cocktail cocktail);

    double getBill();

    void clear();
}
