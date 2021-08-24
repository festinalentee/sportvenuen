package com.kbien.sportvenuenserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "user", schema = "public", catalog = "d2uh5pi93osva1")
public class User {
    @Id @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;
    private String email;
    private String password;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Venue> venues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
