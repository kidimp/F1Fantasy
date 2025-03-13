package com.example.f1fantasy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @NotNull
    @Size(max = 255)
    @Column(name = "circuit_short_name", nullable = false)
    private String circuitShortName;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @NotNull
    @Column(name = "country_key", nullable = false)
    private Integer countryKey;

    @NotNull
    @Size(max = 255)
    @Column(name = "country_name", nullable = false)
    private String countryName;

    @NotNull
    @Size(max = 255)
    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "circuit")
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
        final Circuit that = (Circuit) o;
        return getCircuitKey() != null && Objects.equals(getCircuitKey(), that.getCircuitKey());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
