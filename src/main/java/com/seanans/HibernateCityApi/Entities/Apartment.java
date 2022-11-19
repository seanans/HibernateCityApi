package com.seanans.HibernateCityApi.Entities;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Apartment")
@Table(name = "Apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "id")
    private UUID id;

    @Column(name = "area",
    nullable = false,
    columnDefinition = "BIGINT")
    private long area;

    @Column(name = "address",
    nullable = false,
    columnDefinition = "TEXT")
    private String address;

    public Apartment() {
    }

    public Apartment(long area, String address) {
        this.area = area;
        this.address = address;
    }
    public Apartment(UUID id, long area, String address) {
        this.id = id;
        this.area = area;
        this.address = address;
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
