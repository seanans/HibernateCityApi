package com.seanans.HibernateCityApi.DTOs;

import java.util.Set;
import java.util.UUID;

public class ApartmentDto {
    private UUID id;
    private long area;
    private String address;
    private Set<PersonDtoHelp> personDtoHelps;

    public ApartmentDto() {
    }

    public ApartmentDto(long area, String address) {
        this.area = area;
        this.address = address;
    }

    public ApartmentDto(UUID id, long area, String address) {
        this.id = id;
        this.area = area;
        this.address = address;
    }

    public ApartmentDto(long area, String address, Set<PersonDtoHelp> personDtoHelps) {
        this.area = area;
        this.address = address;
        this.personDtoHelps = personDtoHelps;
    }

    public ApartmentDto(UUID id, long area, String address, Set<PersonDtoHelp> personDtoHelps) {
        this.id = id;
        this.area = area;
        this.address = address;
        this.personDtoHelps = personDtoHelps;
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

    public Set<PersonDtoHelp> getPersonDtoHelps() {
        return personDtoHelps;
    }

    public void setPersonDtoHelps(Set<PersonDtoHelp> personDtoHelps) {
        this.personDtoHelps = personDtoHelps;
    }
}
