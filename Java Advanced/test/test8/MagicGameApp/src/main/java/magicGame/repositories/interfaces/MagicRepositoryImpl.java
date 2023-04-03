package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;

import java.util.*;

public class MagicRepositoryImpl implements MagicRepository<Magic>{
    private List<Magic> data;

    public MagicRepositoryImpl() {
        data = new ArrayList<>();
    }

    @Override
    public Collection<Magic> getData() {
        return Collections.unmodifiableCollection(data);
    }

    @Override
    public void addMagic(Magic model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_REPOSITORY);
        }
        boolean canAdd = true;
        for (Magic m : data) {
            if (m.getName().equals(model.getName())) {
                canAdd=false;
            }
        }
        if (canAdd) {
            data.add(model);
        }
    }

    @Override
    public boolean removeMagic(Magic model) {
        return data.removeIf(magic -> magic.getName().equals(model.getName()));
    }

    @Override
    public Magic findByName(String name) {
        return data.stream()
                .filter(magic -> magic.getName().equals(name))
                .findFirst().orElse(null);
    }
}
