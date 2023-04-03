package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DiscovererRepository implements Repository<Discoverer> {
    private Collection<Discoverer> discoverers;
    public DiscovererRepository() {
        this.discoverers = new ArrayList<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(discoverers);
    }

    @Override
    public void add(Discoverer entity) {
        for (var d : discoverers) {
            if (d.getName().equals(entity.getName())) {
                return;
            }
        }
        discoverers.add(entity);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return discoverers.removeIf(d -> d.getName().equals(entity.getName()));
    }

    @Override
    public Discoverer byName(String name) {
        for (Discoverer discoverer : discoverers) {
            if (discoverer.getName().equals(name)) {
                return discoverer;
            }
        }
        return null;
    }
}
