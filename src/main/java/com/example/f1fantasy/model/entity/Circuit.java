package com.example.f1fantasy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "circuit")
public class Circuit {

    @Id
    @Column(name = "circuit_key")
    private Integer circuitKey;

    @Column(name = "circuit_short_name", nullable = false, length = 255)
    private String circuitShortName;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "country_key", nullable = false)
    private Integer countryKey;

    @Column(name = "country_name", nullable = false, length = 255)
    private String countryName;

    @Column(name = "location", nullable = false, length = 255)
    private String location;
}
