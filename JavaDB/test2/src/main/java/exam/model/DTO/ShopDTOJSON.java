package exam.model.DTO;

import java.io.Serializable;

public class ShopDTOJSON implements Serializable {
    String name;

    public ShopDTOJSON() {
    }

    public String getName() {
        return name;
    }

    public ShopDTOJSON setName(String name) {
        this.name = name;
        return this;
    }
}