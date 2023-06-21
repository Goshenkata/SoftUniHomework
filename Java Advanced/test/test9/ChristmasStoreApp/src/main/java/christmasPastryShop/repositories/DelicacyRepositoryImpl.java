package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class DelicacyRepositoryImpl implements DelicacyRepository<Delicacy> {
    private  Collection<Delicacy> models;

    public DelicacyRepositoryImpl() {
        models = new ArrayList<>();
    }

    @Override
    public Delicacy getByName(String name) {
        for (Delicacy delicacy : models) {
            if (delicacy.getName().equals(name)) {
                return delicacy;
            }
        }
        return null;
    }

    @Override
    public Collection<Delicacy> getAll() {
        return models;
    }

    @Override
    public void add(Delicacy delicacy) {
        models.add(delicacy);
    }
}
