package com.kbien.sportvenuenserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;
    String venueType;
    String venueName;
    String streetName;
    String streetNumber;
    String city;
    String postcode;
    String country;
    @Column(columnDefinition = "text")
    String description;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<OpeningDetails> openingDetails = new ArrayList<>();
}
