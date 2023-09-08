package com.gusia.backend.home;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    private Integer pid;
    private String name;

    public Person() {

    }

    public Person(int pid, String name) {
        this.pid = pid;
        this.name = name;
    }

    public Person(Person person) {
        this.pid = person.pid;
        this.name = person.name;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String toString() {
        return "pid: " + this.pid + ", name: " + this.name;
    }
}
