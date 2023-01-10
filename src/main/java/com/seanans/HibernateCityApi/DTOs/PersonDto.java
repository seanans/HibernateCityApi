package com.seanans.HibernateCityApi.DTOs;

import java.util.Set;
import java.util.UUID;

public class PersonDto {

    private UUID id;
    private String name;
    private String surname;
    private Set<ApartmentDtoHelp> apartmentDtoHelps;

    public PersonDto() {
    }

    public PersonDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public PersonDto(UUID id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public PersonDto(String name, String surname, Set<ApartmentDtoHelp> apartmentDtoHelps) {
        this.name = name;
        this.surname = surname;
        this.apartmentDtoHelps = apartmentDtoHelps;
    }

    public PersonDto(UUID id, String name, String surname, Set<ApartmentDtoHelp> apartmentDtoHelps) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.apartmentDtoHelps = apartmentDtoHelps;
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

    public Set<ApartmentDtoHelp> getApartmentDtoHelps() {
        return apartmentDtoHelps;
    }

    public void setApartmentDtoHelps(Set<ApartmentDtoHelp> apartmentDtoHelps) {
        this.apartmentDtoHelps = apartmentDtoHelps;
    }
}
