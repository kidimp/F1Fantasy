package com.example.f1fantasy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
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
    @Column(name = "constructor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор конструктора

    @Column(name = "broadcast_name", nullable = false)
    private String broadcastName; // Название команды в трансляциях

    @Column(name = "full_name", nullable = false)
    private String fullName; // Полное название команды

    @Column(name = "name_acronym", nullable = false)
    private String nameAcronym; // Аббревиатура названия команды

    @Column(name = "logo_url", nullable = false)
    private String logoUrl; // Ссылка на логотип команды

    @Column(name = "country_code", nullable = false)
    private String countryCode; // Код страны

    @Column(name = "country_full_name", nullable = false)
    private String countryFullName; // Полное название страны

    @Column(name = "base", nullable = false)
    private String base; // Местоположение базы команды

    @Column(name = "team_colour", nullable = false)
    private String teamColour; // Цвет команды (в шестнадцатеричном формате)

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
