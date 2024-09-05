package com.example.smartHouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accommodation")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "amenities")
    private String amenities;

    @Column(name = "minGuest")
    private Integer minGuest;

    @Column(name = "maxGuest")
    private Integer maxGuest;

    @Column(name = "type")
    private String type;

    @ElementCollection
    private List<LocalDate> available = new ArrayList<>();

    @ElementCollection
    private List<LocalDate> taken = new ArrayList<>();

    @Column(name = "price")
    private Double price;
}
