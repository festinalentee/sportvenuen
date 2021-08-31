package com.kbien.sportvenuenserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
