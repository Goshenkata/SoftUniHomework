package entities;

public class Minion {
    Integer id;
    String name;
    int age;
    int town_id;

    public Minion() {
    }

    public int getId() {
        return id;
    }

    public Minion setId(int id) {
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

    public int getAge() {
        return age;
    }

    public Minion setAge(int age) {
        this.age = age;
        return this;
    }

    public int getTown_id() {
        return town_id;
    }

    public Minion setTown_id(int town_id) {
        this.town_id = town_id;
        return this;
    }
}
