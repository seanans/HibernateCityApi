package com.seanans.HibernateCityApi.Entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Apartment")
@Table(name = "Apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private UUID uuid;

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

    public Apartment(UUID uuid, long area, String address) {
        this.uuid = uuid;
        this.area = area;
        this.address = address;
    }

    public Apartment(long area, String address) {
        this.area = area;
        this.address = address;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
