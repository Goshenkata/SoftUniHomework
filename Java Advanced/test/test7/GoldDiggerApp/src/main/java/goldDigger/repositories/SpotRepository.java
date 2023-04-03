package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SpotRepository implements Repository<Spot> {
    List<Spot> spots;

    public SpotRepository() {
        this.spots = new ArrayList<>();
    }
    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(spots);
    }

    @Override
    public void add(Spot entity) {
        for (var d : spots) {
            if (d.getName().equals(entity.getName())) {
                return;
            }
        }
        spots.add(entity);
    }

    @Override
    public boolean remove(Spot entity) {
        return spots.removeIf(d -> d.getName().equals(entity.getName()));
    }

    @Override
    public Spot byName(String name) {
        for (var s : spots) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
