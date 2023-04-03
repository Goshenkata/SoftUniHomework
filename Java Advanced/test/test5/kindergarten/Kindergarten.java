package kindergarten;

import java.util.ArrayList;
import java.util.List;

public class Kindergarten {
    private String name;
    private int capacity;
    List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>();
    }

    public boolean addChild(Child child) {
        if (registry.size() + 1 <= capacity) {
            registry.add(child);
            return true;
        }
        return false;
    }

    public boolean removeChild(String firstName) {
        for (int i = 0; i < registry.size(); i++) {
            if (registry.get(i).getFirstName().equals(firstName)) {
                registry.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getChildrenCount() {
        return registry.size();
    }

    public Child getChild(String firstName) {
        for (Child child : registry) {
            if (child.getFirstName().equals(firstName)) {
                return child;
            }
        }
        return null;
    }

    public String registryReport() {
        String out = String.format("Registered children in %s:%n", name);
        List<Child> copy = new ArrayList<>(registry);

        copy.sort((c1, c2) -> {
            int compareAge = Integer.compare(c1.age, c2.age);
            if (compareAge == 0) {
                int compareFirstName = c1.getFirstName().compareTo(c2.getFirstName());
                if (compareFirstName == 0) {
                    return c1.getLastName().compareTo(c2.getLastName());
                }
                return compareFirstName;
            }
            return  compareAge;
        });

        List<String> strings = new ArrayList<>();
        for (Child child : copy) {
            strings.add(child.toString());
        }
        out += String.join("\n--\n", strings);
        return out;
    }
}
