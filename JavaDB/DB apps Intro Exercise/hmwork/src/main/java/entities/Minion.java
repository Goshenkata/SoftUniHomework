package entities;

public class Minion {
    Integer id;
    String name;
    Integer age;
    Integer town_id;

    public Minion() {
    }

    public Integer getId() {
        return id;
    }

    public Minion setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Minion setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Minion setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getTown_id() {
        return town_id;
    }

    public Minion setTown_id(Integer town_id) {
        this.town_id = town_id;
        return this;
    }
}
