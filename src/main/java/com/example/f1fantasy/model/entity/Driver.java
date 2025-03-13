package com.example.f1fantasy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "broadcast_name", nullable = false)
    private String broadcastName;

    @NotNull
    @Size(max = 255)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 255)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Size(max = 255)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "name_acronym", nullable = false, length = 3)
    private String nameAcronym;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @NotNull
    @Column(name = "driver_number", unique = true, nullable = false)
    private Integer driverNumber;

    @Column(name = "headshot_url")
    private String headshotUrl;

    @ManyToMany
    @JoinTable(
        name = "driver_constructor",
        joinColumns = @JoinColumn(name = "driver_id"),
        inverseJoinColumns = @JoinColumn(name = "constructor_id")
    )
    @ToString.Exclude
    private List<Constructor> constructors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "driver_grand_prix",
        joinColumns = @JoinColumn(name = "driver_id"),
        inverseJoinColumns = @JoinColumn(name = "grand_prix_id")
    )
    @ToString.Exclude
    private List<GrandPrix> grandPrixList = new ArrayList<>();

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        final Driver that = (Driver) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
