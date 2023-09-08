package com.gusia.backend.idea;

import com.gusia.backend.home.Person;
import jakarta.persistence.*;

@Entity
public class Idea {
    @Id
    private Integer iid;
    private String title;
    @ManyToOne
    private Person person;

    public Idea() {

    }

    public Idea(int iid, String title, Person person) {
        this.iid = iid;
        this.title = title;
        this.person = person;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public Person getPerson() {
        return person;
    }

    // TODO - to jest jakieś okropne, ale nie wiem jak to zrobić lepiej
    public void setPerson(int pid) {
        this.person = new Person(pid, "");
    }
}
