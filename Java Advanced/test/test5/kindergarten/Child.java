package kindergarten;

public class Child {
    public String firstName;
    public String lastName;
    public int age;
    public String parentName;
    public String contactNumber;

    public Child(String firstName, String lastName, int age, String parentName, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.parentName = parentName;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return String.format("Child: %s %s, Age: %d, Contact info: %s - %s", firstName, lastName, age, parentName, contactNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public Child setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Child setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Child setAge(int age) {
        this.age = age;
        return this;
    }

    public String getParentName() {
        return parentName;
    }

    public Child setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Child setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }
}
