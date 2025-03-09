package com.example.f1fantasy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver_season",
        uniqueConstraints = @UniqueConstraint(columnNames = {"driver_id", "constructor_id", "season_year"}))
public class DriverSeason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "constructor_id", nullable = false)
    private Constructor constructor;

    @ManyToOne
    @JoinColumn(name = "season_year", nullable = false)
    private Season season;

    @Column(nullable = false)
    private Integer racesParticipated; // Количество гонок за команду в сезоне

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriverSeason that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
