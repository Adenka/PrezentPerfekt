package com.gusia.backend.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private UUID pid;
    private String name;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    // Ten setter jest ważny!
    public void setName(String name) {
        this.name = name;
    }

    // Te gettery też!
    public UUID getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public void setPid(UUID pid) {
        this.pid = pid;
    }

    public String toString() {
        return "pid: " + this.pid + ", name: " + this.name;
    }
}
