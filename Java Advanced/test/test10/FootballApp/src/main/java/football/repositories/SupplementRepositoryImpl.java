package football.repositories;

import football.entities.supplement.Supplement;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.List;

public class SupplementRepositoryImpl implements SupplementRepository{
    private List<Supplement> models;

    public SupplementRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public void add(Supplement supplement) {
        models.add(supplement);
    }

    @Override
    public boolean remove(Supplement supplement) {
        return models.remove(supplement);
    }

    @Override
    public Supplement findByType(String type) {
        for (Supplement model : models) {
            if (model.getClass().getSimpleName().equals(type)) {
                return model;
            }
        }
        return null;
    }
}
