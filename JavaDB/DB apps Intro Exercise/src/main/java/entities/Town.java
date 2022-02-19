package entities;

public class Town {
    int id;
    String name;
    String country;

    public Town() {
    }

    public int getId() {
        return id;
    }

    public Town setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Town setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Town setCountry(String country) {
        this.country = country;
        return this;
    }

}
