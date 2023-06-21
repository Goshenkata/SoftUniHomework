package magicGame.core;

import magicGame.common.ExceptionMessages;
import magicGame.common.OutputMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepository;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepository;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControllerImpl implements Controller {
    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        magics = new MagicRepositoryImpl();
        magicians = new MagicianRepositoryImpl();
        region = new RegionImpl();
    }
    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        if (type.equals("RedMagic")) {
            magic = new RedMagic(name, bulletsCount);
        } else if (type.equals("BlackMagic")) {
            magic = new BlackMagic(name, bulletsCount);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_TYPE);
        }
        magics.addMagic(magic);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGIC, name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magic = magics.findByName(magicName);
        if (magic == null) {
            throw new NullPointerException(ExceptionMessages.MAGIC_CANNOT_BE_FOUND);
        }
        Magician magician;
        if (type.equals("Wizard")) {
            magician = new Wizard(username, health, protection, magic);
        } else if (type.equals("BlackWidow")) {
            magician = new BlackWidow(username, health, protection, magic);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_TYPE);
        }
        magicians.addMagician(magician);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN, username);
    }

    @Override
    public String startGame() {
        return region.start(magicians.getData());
    }

    @Override
    public String report() {
        List<String> out = new ArrayList<>();
        magicians.getData()
                .stream().sorted((m1, m2) -> {
                    int compare = Integer.compare(m1.getHealth(), m2.getHealth());
                    if (compare == 0) {
                        int i = m1.getUsername().compareTo(m2.getUsername());
                        if (i == 0) {
                            return m1.getClass().getSimpleName()
                                    .compareTo(m2.getClass().getSimpleName());
                        }
                        return i;
                    }
                    return compare;
                }).forEach(magician -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("%s: %s", magician.getClass().getSimpleName(), magician.getUsername()));
                    sb.append(System.lineSeparator());
                    sb.append(String.format("Health: %d", magician.getHealth()));
                    sb.append(System.lineSeparator());
                    sb.append(String.format("Protection: %d", magician.getProtection()));
                    sb.append(System.lineSeparator());
                    sb.append(String.format("Magic: %s", magician.getMagic().getName()));
                    out.add(sb.toString().trim());
                });
        return String.join(System.lineSeparator(), out);
    }
}
