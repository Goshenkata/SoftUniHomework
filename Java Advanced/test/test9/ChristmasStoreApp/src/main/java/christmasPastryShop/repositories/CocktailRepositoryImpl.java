package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.CocktailRepository;

import java.util.ArrayList;
import java.util.Collection;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {
    private  Collection<Cocktail> models;

    public CocktailRepositoryImpl() {
        models = new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        for (Cocktail model : models) {
            if (model.getName().equals(name)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public Collection<Cocktail> getAll() {
        return models;
    }

    @Override
    public void add(Cocktail cocktail) {
        models.add(cocktail);
    }
}
