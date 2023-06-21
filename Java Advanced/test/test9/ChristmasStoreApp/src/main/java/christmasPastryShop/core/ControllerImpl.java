package christmasPastryShop.core;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.common.OutputMessages;
import christmasPastryShop.common.enums.BoothType;
import christmasPastryShop.common.enums.CocktailType;
import christmasPastryShop.common.enums.DelicacyType;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.OpenBooth;
import christmasPastryShop.entities.booths.PrivateBooth;
import christmasPastryShop.entities.cocktails.Hibernation;
import christmasPastryShop.entities.cocktails.MulledWine;
import christmasPastryShop.entities.delicacies.BaseDelicacy;
import christmasPastryShop.entities.delicacies.Gingerbread;
import christmasPastryShop.entities.delicacies.Stolen;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.ArrayList;

public class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double sum = 0;
    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        DelicacyType delicacyType = DelicacyType.valueOf(type);
        Delicacy delicacy;
        switch (delicacyType) {
            case Gingerbread:
                delicacy = new Gingerbread(name, price);
                break;
            case Stolen:
                delicacy = new Stolen(name, price);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + delicacyType);
        }
        Delicacy byName = delicacyRepository.getByName(name);
        if (byName == null) {
            delicacyRepository.add(delicacy);
            return String.format(OutputMessages.DELICACY_ADDED, name, type);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail;
        CocktailType cocktailType = CocktailType.valueOf(type);
        switch (cocktailType) {
            case MulledWine:
                cocktail = new MulledWine(name, size, brand);
                break;
            case Hibernation:
                cocktail = new Hibernation(name, size, brand);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cocktailType);
        }
        Cocktail byName = cocktailRepository.getByName(name);
        if (byName == null) {
            cocktailRepository.add(cocktail);
            return String.format(OutputMessages.COCKTAIL_ADDED, name, brand);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth;
        BoothType boothType = BoothType.valueOf(type);
        switch (boothType) {
            case OpenBooth:
                booth = new OpenBooth(boothNumber, capacity);
                break;
            case PrivateBooth:
                booth = new PrivateBooth(boothNumber, capacity);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + boothType);
        }

        Booth b = boothRepository.getByNumber(boothNumber);
        if (b == null) {
            boothRepository.add(booth);
            return String.format(OutputMessages.BOOTH_ADDED, boothNumber);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BOOTH_EXIST, boothNumber));
        }
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        for (Booth booth : boothRepository.getAll()) {
            if (!booth.isReserved() && booth.getCapacity() >= numberOfPeople) {
                booth.reserve(numberOfPeople);
                return String.format(OutputMessages.BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
            }
        }
        return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        this.sum += bill;
        booth.clear();
        return String.format(OutputMessages.BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(OutputMessages.TOTAL_INCOME, sum);
    }
}
