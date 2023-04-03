package goldDigger.models.spot;

import goldDigger.common.ExceptionMessages;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpotImpl implements Spot {

    private String name;
    public List<String> exibits;
    public SpotImpl(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(ExceptionMessages.SPOT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
        this.exibits = new ArrayList<>();
    }


    @Override
    public Collection<String> getExhibits() {
        return this.exibits;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
