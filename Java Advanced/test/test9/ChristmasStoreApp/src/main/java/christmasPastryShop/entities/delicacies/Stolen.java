package christmasPastryShop.entities.delicacies;

import christmasPastryShop.entities.delicacies.BaseDelicacy;

public class Stolen extends BaseDelicacy {
    private static final double InitialStolenPortion = 200;

    public Stolen(String name, double price) {
        super(name, InitialStolenPortion, price);
    }
}
