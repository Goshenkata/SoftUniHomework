package goldDigger.models.discoverer;

import goldDigger.common.ExceptionMessages;
import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;

public abstract class BaseDiscoverer implements Discoverer {
    private String name;
    private double energy;
    private Museum museum;

    public BaseDiscoverer(String name, double energy) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(ExceptionMessages.DISCOVERER_NAME_NULL_OR_EMPTY);
        }
        if (energy < 0) {
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.name = name;
        this.energy = energy;
        this.museum = new BaseMuseum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public Museum getMuseum() {
        return this.museum;
    }

    @Override
    public boolean canDig() {
        return energy > 0;
    }


    @Override
    public void dig() {
        if (energy - 15 < 0) {
            energy = 0;
        } else {
            energy -= 15;
        }
    }
}
