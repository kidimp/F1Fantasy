package com.example.f1fantasy.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
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
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "constructor")
public class Constructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constructor_id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "broadcast_name", nullable = false)
    private String broadcastName;

    @NotNull
    @Size(max = 255)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "name_acronym", nullable = false, length = 3)
    private String nameAcronym;

    @NotNull
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @NotNull
    @Size(max = 255)
    @Column(name = "country_full_name", nullable = false)
    private String countryFullName;

    @NotNull
    @Size(max = 255)
    @Column(name = "base", nullable = false)
    private String base;

    @NotNull
    @Size(min = 7, max = 7)
    @Column(name = "team_colour", nullable = false, length = 7)
    private String teamColour;

    @ManyToMany(mappedBy = "constructors")
    @ToString.Exclude
    private List<Season> seasons = new ArrayList<>();

    @ManyToMany(mappedBy = "constructors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("lastName ASC")
    @ToString.Exclude
    private List<Driver> drivers = new ArrayList<>();

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
        final Constructor that = (Constructor) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
