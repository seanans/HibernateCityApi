package com.seanans.HibernateCityApi.DTOs;

import java.util.Set;
import java.util.UUID;

public class AddApartmentDto {

    private UUID id;

    private long area;
    private String address;

    private Set<UUID> persons;

    public AddApartmentDto(long area, String address, Set<UUID> persons) {
        this.area = area;
        this.address = address;
        this.persons = persons;
    }

    public AddApartmentDto() {
    }

    public AddApartmentDto(long area, String address) {
        this.area = area;
        this.address = address;
    }

    public AddApartmentDto(UUID id, long area, String address, Set<UUID> persons) {
        this.id = id;
        this.area = area;
        this.address = address;
        this.persons = persons;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<UUID> getPersons() {
        return persons;
    }

    public void setPersons(Set<UUID> persons) {
        this.persons = persons;
    }
}
