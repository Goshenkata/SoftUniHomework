package football.entities.player;

import football.common.ExceptionMessages;

public abstract class BasePlayer implements Player{
    private String name;
    private String nationality;
    private double kg;
    protected int strength;

    public BasePlayer(String name, String nationality, double kg, int strength) {
        setName(name);
        if (nationality == null || nationality.isBlank()) {
            throw new NullPointerException(ExceptionMessages.PLAYER_NATIONALITY_NULL_OR_EMPTY);
        }
        this.nationality = nationality;
        this.kg = kg;
        if (strength <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(ExceptionMessages.PLAYER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void stimulation() {
    }

    @Override
    public double getKg() {
        return kg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrength() {
        return strength;
    }
}
