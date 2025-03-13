package com.example.f1fantasy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Если в будущем появится необходимость разрешить разные Гран-при на одной трассе в одном сезоне, можно добавить
 * в @UniqueConstraint ещё одно поле, например, meeting_key. Тогда ограничение изменится:
 * uniqueConstraints = @UniqueConstraint(columnNames = {"circuit_key", "season_id", "meeting_key"}) <br>
 * Теперь можно проводить несколько Гран-при на одной трассе в одном сезоне, но их идентификатор (meeting_key) должен отличаться.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "grand_prix",
    uniqueConstraints = @UniqueConstraint(columnNames = {"circuit_key", "season_id"})
)
public class GrandPrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grand_prix_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "circuit_key", nullable = false)
    private Circuit circuit;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @Column(name = "date_start", nullable = false)
    private LocalDateTime dateStart;

    @Column(name = "gmt_offset", nullable = false, length = 8)
    private String gmtOffset;

    @Column(name = "meeting_key", nullable = false)
    private Integer meetingKey;

    @Column(name = "meeting_name", nullable = false)
    private String meetingName;

    @Column(name = "meeting_official_name", nullable = false)
    private String meetingOfficialName;

    @ManyToMany(mappedBy = "grandPrixList")
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
        final GrandPrix that = (GrandPrix) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
