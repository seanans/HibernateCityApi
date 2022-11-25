package com.seanans.HibernateCityApi.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Person")
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "id")
    private UUID id;
    @Column(name = "name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;
    @Column(name = "surname",
            nullable = false,
            columnDefinition = "TEXT")
    private String surname;

    @ManyToMany(targetEntity = Apartment.class, cascade = { CascadeType.REMOVE })
    @JoinTable(name = "persons_apartments",
    joinColumns = { @JoinColumn(name = "person_id")},
    inverseJoinColumns = { @JoinColumn(name = "apartment_id")})
    private Set<Apartment> apartments;

    public Person() {
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(UUID id, String name, String surname) {
        this.id = id;
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

    public Person(String name, String surname, Set<Apartment> apartments) {
        this.name = name;
        this.surname = surname;
        this.apartments = apartments;
    }

    public Person(UUID id, String name, String surname, Set<Apartment> apartments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.apartments = apartments;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }
}
