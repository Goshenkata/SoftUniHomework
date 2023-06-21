package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.BaseField;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.BaseSupplement;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    SupplementRepository supplement;
    Collection<Field> fields;

    public ControllerImpl() {
        fields = new ArrayList<>();
        supplement = new SupplementRepositoryImpl();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }
        fields.add(field);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement sup;
        switch (type) {
            case "Powdered":
                sup = new Powdered();
                break;
            case "Liquid":
                sup = new Liquid();
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        supplement.add(sup);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                Supplement byType = supplement.findByType(supplementType);
                if (byType != null) {
                    field.getSupplements().add(byType);
                    return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
                } else {
                    break;
                }
            }
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;
        boolean canPlay = false;
        boolean isMan = false;
        switch (playerType) {
            case "Men":
                player = new Men(playerName, nationality, strength);
                isMan = true;
                break;
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                if (field.getClass().getSimpleName().equals("ArtificialTurf")) {
                    if (!isMan) {
                        canPlay = true;
                    }
                } else if (field.getClass().getSimpleName().equals("NaturalGrass")) {
                    if (isMan) {
                        canPlay = true;
                    }
                }
                field.addPlayer(player);
                if (canPlay) {
                    return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
                }
            }
        }
        return ConstantMessages.FIELD_NOT_SUITABLE;
    }

    @Override
    public String dragPlayer(String fieldName) {
        int count = 0;
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                field.drag();
                count = field.getPlayers().size();
                break;
            }
        }
        return String.format(ConstantMessages.PLAYER_DRAG, count);
    }

    @Override
    public String calculateStrength(String fieldName) {
        int sum = 0;
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                for (Player player : field.getPlayers()) {
                    sum += player.getStrength();
                }
            }
        }
        return String.format(ConstantMessages.STRENGTH_FIELD,fieldName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            sb.append(field.getInfo());
        }
        return sb.toString();
    }
}
