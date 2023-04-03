package shelter;

import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private List<Animal> data;
    private int capacity;

    public String getStatistics() {
        String out = "The shelter has the following animals:\n";
        for (Animal datum : data) {
            out += String.format("%s %s\n", datum.getName(), datum.getCaretaker());
        }
        return out;
    }
    public int getCount() {
        return data.size();
    }
    public Animal getOldestAnimal() {
        Animal oldest = new Animal("yong", Integer.MIN_VALUE, "cock");
        for (Animal an : data) {
            if (an.getAge() > oldest.getAge()) {
                oldest = an;
            }
        }
        return oldest;
    }
    public Animal getAnimal(String name, String caretaker) {
        for (Animal animal : data) {
            if (animal.getName().equals(name) && animal.getCaretaker().equals(caretaker)) {
                return animal;
            }
        }
        return null;
    }
    public boolean remove(String name) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName().equals(name)) {
                data.remove(i);
                return true;
            }
        }
        return false;
    }
    public void add(Animal animal) {
        if (data.size() < capacity) {
            data.add(animal);
        }
    }
    public Shelter(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public List<Animal> getDate() {
        return data;
    }

    public Shelter setDate(List<Animal> date) {
        this.data = date;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Shelter setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
}
