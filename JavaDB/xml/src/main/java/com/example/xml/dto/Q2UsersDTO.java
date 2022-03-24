package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q2UsersDTO implements Serializable {
    @XmlElement(name = "user")
    List<Q2UserDTO> users;


    public Q2UsersDTO() {
    }

    public List<Q2UserDTO> getUsers() {
        return users;
    }

    public Q2UsersDTO setUsers(List<Q2UserDTO> users) {
        this.users = users;
        return this;
    }
}
