package com.seanans.HibernateCityApi.DTOs;

import java.util.UUID;

public class ApartmentDtoHelp {

    private UUID id;
    private long area;
    private String address;

    public ApartmentDtoHelp(UUID id, long area, String address) {
        this.id = id;
        this.area = area;
        this.address = address;
    }

    public ApartmentDtoHelp(long area, String address) {
        this.area = area;
        this.address = address;
    }

    public ApartmentDtoHelp() {
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
}
