package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RegionImpl implements Region{

    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> blackWidows = new LinkedList<>();
        List<Magician> wizards = new LinkedList<>();
        for (Magician magician : magicians) {
            if (magician instanceof  BlackWidow) {
                blackWidows.add(magician);
            } else {
                wizards.add(magician);
            }
        }
        while (!blackWidows.isEmpty() && !wizards.isEmpty()) {
            Wizard wizard = (Wizard) wizards.get(0);
            BlackWidow blackWidow = (BlackWidow) blackWidows.get(0);
            blackWidow.takeDamage(wizard.getMagic().fire());
            if (blackWidow.isAlive()) {
                wizard.takeDamage(blackWidow.getMagic().fire());
                if (!wizard.isAlive()) {
                    wizards.remove(wizard);
                }
            } else {
                blackWidows.remove(blackWidow);
            }
        }
        if (wizards.isEmpty()) {
            return "Black widows win!";
        } else {
            return "Wizards win!";
        }
    }
}
