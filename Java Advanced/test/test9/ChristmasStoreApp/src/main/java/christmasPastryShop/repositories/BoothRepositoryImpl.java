package christmasPastryShop.repositories;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;

import java.util.ArrayList;
import java.util.Collection;

public class BoothRepositoryImpl implements BoothRepository<Booth> {
    private Collection<Booth> models;

    public BoothRepositoryImpl() {
        models = new ArrayList<>();
    }

    @Override
    public Booth getByNumber(int number) {
        for (Booth model : models) {
            if (model.getBoothNumber() == number) {
                return model;
            }
        }
        return null;
    }

    @Override
    public Collection<Booth> getAll() {
        return models;
    }

    @Override
    public void add(Booth booth) {
        models.add(booth);
    }
}
