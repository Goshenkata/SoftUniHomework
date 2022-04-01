package exam.model.DTO;

import java.io.Serializable;

public class TownDTOJson implements Serializable {
    String name;

    public TownDTOJson() {
    }

    public String getName() {
        return name;
    }

    public TownDTOJson setName(String name) {
        this.name = name;
        return this;
    }
}