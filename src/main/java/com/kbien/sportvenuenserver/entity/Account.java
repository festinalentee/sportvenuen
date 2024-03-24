package com.kbien.sportvenuenserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Venue> venues = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Venue> favourites = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<Booking> bookings;
}
