package com.invillia;

import java.util.StringJoiner;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Team() {
    }

    public Team(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "Team(", ")")
                .add("id=" + id)
                .add("name=" + name)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
