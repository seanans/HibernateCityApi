package com.seanans.HibernateCityApi.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Apartment")
@Table(name = "apartment")
public class Apartment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "apartment_id")
    private UUID id;

    @Column(name = "area",
    nullable = false,
    columnDefinition = "BIGINT")
    private long area;

    @Column(name = "address",
    nullable = false,
    columnDefinition = "TEXT")
    private String address;

    @ManyToMany
    @JoinTable(name = "persons_apartments",
            joinColumns = { @JoinColumn(name = "apartment_id")},
            inverseJoinColumns = { @JoinColumn(name = "person_id")})
    private Set<Person> persons = new HashSet<Person>(0);

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

    public Apartment(long area, String address, Set<Person> persons) {
        this.area = area;
        this.address = address;
        this.persons = persons;

    }

    public Apartment(UUID id, long area, String address, Set<Person> persons) {
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

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
