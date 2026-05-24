package com.udemy.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class List {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    private Long id;

    @Column( name = "name", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    private java.util.List<Item> items;
}
