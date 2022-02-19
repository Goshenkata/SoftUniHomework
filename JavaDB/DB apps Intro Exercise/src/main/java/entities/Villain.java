package entities;

public class Villain {
    Integer id;
    String name;
    String evillnesFactor;

    public Villain() {
    }

    public int getId() {
        return id;
    }

    public Villain setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Villain setName(String name) {
        this.name = name;
        return this;
    }

    public String getEvillnesFactor() {
        return evillnesFactor;
    }

    public Villain setEvillnesFactor(String evillnesFactor) {
        this.evillnesFactor = evillnesFactor;
        return this;
    }
}
