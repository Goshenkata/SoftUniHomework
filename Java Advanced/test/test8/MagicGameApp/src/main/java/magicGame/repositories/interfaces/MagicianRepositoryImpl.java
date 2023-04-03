package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magicians.Magician;
import magicGame.models.magics.Magic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MagicianRepositoryImpl implements MagicianRepository<Magician> {

    private List<Magician> data;
    public MagicianRepositoryImpl() {
        data = new ArrayList<>();
    }

    @Override
    public Collection<Magician> getData() {
        return Collections.unmodifiableCollection(data);
    }

    @Override
    public void addMagician(Magician model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_REPOSITORY);
        }
        boolean canAdd = true;
        for (Magician m : data) {
            if (m.getUsername().equals(model.getUsername())) {
                canAdd=false;
            }
        }
        if (canAdd) {
            data.add(model);
        }
    }

    @Override
    public boolean removeMagician(Magician model) {
        return data.removeIf(magician -> magician.getUsername().equals(model.getUsername()));
    }

    @Override
    public Magician findByUsername(String name) {
        return data.stream()
                .filter(magician -> magician.getUsername().equals(name))
                .findFirst().orElse(null);
    }
}
