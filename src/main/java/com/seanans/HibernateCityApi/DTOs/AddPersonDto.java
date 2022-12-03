package com.seanans.HibernateCityApi.DTOs;

import java.util.Set;
import java.util.UUID;

public class AddPersonDto {

    private UUID id;

    private String name;
    private String surname;
    private Set<UUID> apartments;

    public AddPersonDto(String name, String surname, Set<UUID> apartments) {
        this.name = name;
        this.surname = surname;
        this.apartments = apartments;
    }

    public AddPersonDto() {
    }

    public AddPersonDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public AddPersonDto(UUID id, String name, String surname, Set<UUID> apartments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.apartments = apartments;
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

    public Set<UUID> getApartments() {
        return apartments;
    }

    public void setApartments(Set<UUID> apartments) {
        this.apartments = apartments;
    }
}
