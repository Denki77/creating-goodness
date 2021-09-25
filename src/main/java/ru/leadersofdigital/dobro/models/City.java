package ru.leadersofdigital.dobro.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cities")
public class City extends AttributeObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;


    public City () {}

    public City (Long id) {
        this.id = id;
    }

}
