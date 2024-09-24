package com.tmtbackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "airports")
@EntityListeners(AuditingEntityListener.class)
public class Airports {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "ident")
    private String ident;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude_deg")
    private double latitudeDeg;

    @Column(name = "longitude_deg")
    private double longitudeDeg;

    @Column(name = "elevation_ft")
    private int elevationFt;

    @Column(name = "continent")
    private String continent;

    @Column(name = "iso_country")
    private String isoCountry;

    @Column(name = "iso_region")
    private String isoRegion;

    @Column(name = "municipality")
    private String municipality;
}
