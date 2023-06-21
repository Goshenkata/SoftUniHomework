package robotService.repositories;

import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SupplementRepository implements Repository{
    private Collection<Supplement> supplements;

    public SupplementRepository() {
        this.supplements = new ArrayList<>();
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);

    }

    @Override
    public boolean removeSupplement(Supplement supplement) {

        return supplements.remove(supplement);
    }

    @Override
    public Supplement findFirst(String type) {
        for (Supplement supplement : supplements) {
            if (supplement.getClass().getSimpleName().equals(type)) {
                return supplement;
            }
        }
        return null;
    }
}
