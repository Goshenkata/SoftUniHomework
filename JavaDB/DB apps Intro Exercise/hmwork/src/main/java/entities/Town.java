package entities;

public class Town {
    Integer id;
    String name;
    String country;

    public Town() {
    }

    public Integer getId() {
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
