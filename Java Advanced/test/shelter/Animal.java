package shelter;

public class Animal {
    private String name;
    private int age;
    private String caretaker;

    public Animal(String name, int age, String caretaker) {
        this.name = name;
        this.age = age;
        this.caretaker = caretaker;
    }

    @Override
    public String toString() {
        return String.format("%s %d (%s)", name, age,caretaker);
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Animal setAge(int age) {
        this.age = age;
        return this;
    }

    public String getCaretaker() {
        return caretaker;
    }

    public Animal setCaretaker(String caretaker) {
        this.caretaker = caretaker;
        return this;
    }
}
