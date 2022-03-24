package com.example.xml.dto;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q4Users implements Serializable {
    @XmlAttribute(name = "count")
    Integer count;
    @XmlElement(name = "user")
    List<Q4User> users;

    public Q4Users() {
    }

    public Integer getCount() {
        return count;
    }

    public Q4Users setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<Q4User> getUsers() {
        return users;
    }

    public Q4Users setUsers(List<Q4User> users) {
        this.users = users;
        return this;
    }
}
