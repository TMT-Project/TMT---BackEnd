package com.tmtbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Table(name = "countries")
@Entity
public class Countries {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(updatable = false,name = "code")
    private String code;

    @Column(updatable = false,name = "name")
    private String name;
}
