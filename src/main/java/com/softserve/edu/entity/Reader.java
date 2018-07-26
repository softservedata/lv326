package com.softserve.edu.entity;

import java.util.Set;

public class Reader implements IEntity {
    private Long id;
    private String name;
    private Set<Using> using;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Using> getUsing() {
        return using;
    }

    public void setUsing(Set<Using> using) {
        this.using = using;
    }

}
