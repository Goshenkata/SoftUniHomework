package christmasPastryShop.entities.delicacies;

import christmasPastryShop.entities.delicacies.BaseDelicacy;

public class Gingerbread extends BaseDelicacy {
    private static final double InitialGingerbreadPortion = 200;
    public Gingerbread(String name, double price) {
        super(name, InitialGingerbreadPortion, price);
    }
}
