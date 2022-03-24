package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDTO implements Serializable {
    @XmlElement(name = "user")
    List<UserImportDTO> users;

    public List<UserImportDTO> getUsers() {
        return users;
    }

    public UsersDTO setUsers(List<UserImportDTO> users) {
        this.users = users;
        return this;
    }
}
