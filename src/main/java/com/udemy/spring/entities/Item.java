package com.udemy.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
public class Item {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    @Size(min = 3,  max = 255, message = "Insira pelo menos 3 caracteres")
    @NotBlank
    @NotNull
    private String description;

    @Column(name = "checked", nullable = false)
    private Boolean checked = false;

    @ManyToOne
    @JsonIgnore
    private List list;


}
