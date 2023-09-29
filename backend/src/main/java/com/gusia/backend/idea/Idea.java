package com.gusia.backend.idea;

import com.gusia.backend.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Idea {
    @Id
    @GeneratedValue
    private UUID iid;
    @NotBlank(message = "Enter a title!")
    private String title;
    @ManyToOne
    private Person person;

    public Idea() {

    }

    public Idea(UUID iid, String title, Person person) {
        this.iid = iid;
        this.title = title;
        this.person = person;
    }

    public UUID getIid() {
        return iid;
    }

    public void setIid(UUID iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "title: " + title /*+ ", person: " + person.getName()*/;
    }
}
