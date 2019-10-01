package com.invillia.jdbc;

import java.util.StringJoiner;

public class Team {

    private final Long id;

    private final String name;

    public Team(final Long id, final String name) {
        this.id = id;
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

    public String getName() {
        return name;
    }
}
