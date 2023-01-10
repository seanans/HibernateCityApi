package com.seanans.HibernateCityApi.DTOs;

import java.util.UUID;

public class PersonDtoHelp {
    private UUID id;
    private String name;
    private String surname;

    public PersonDtoHelp(UUID id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public PersonDtoHelp() {
    }

    public PersonDtoHelp(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
