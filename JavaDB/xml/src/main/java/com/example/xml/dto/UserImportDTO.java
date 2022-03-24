package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportDTO implements Serializable {
    @XmlAttribute(name = "first-name")
    String firstName;
    @XmlAttribute(name = "last-name")
    String lastName;
    @XmlAttribute(name = "age")
    int age;

    public UserImportDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserImportDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserImportDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserImportDTO setAge(int age) {
        this.age = age;
        return this;
    }
}
