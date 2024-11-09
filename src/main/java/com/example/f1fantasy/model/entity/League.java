//package com.example.f1fantasy.model.entity;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import jakarta.validation.constraints.Size;
//import org.hibernate.proxy.HibernateProxy;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "league")
//public class League {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull
//    @Size(max = 256)
//    @Column(name = "league_code", length = 256)
//    private String leagueCode;
//
//    @Column(name = "league_name", length = 256)
//    private String leagueName;
//
//    @Size(max = 256)
//    @Column(name = "description", length = 256)
//    private String description;
//
//    @Column(name = "player_limit", nullable = false)
//    private int limit; // Максимальное количество игроков в лиге
//
//    @Column(name = "is_active", nullable = false)
//    private boolean active; // Флаг, указывающий, активна ли лига
//
//    @ManyToMany(mappedBy = "leagues")
//    @ToString.Exclude
//    private Set<Player> players; // Игроки в лиге
//
//    @Column(name = "points_system")
//    private String pointsSystem; // Система начисления очков в json
//
//    @Column(name = "created_at", nullable = false)
//    private LocalDateTime createdAt;
//
//    @Override
//    public final boolean equals(final Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null) {
//            return false;
//        }
//        if (o.getClass() != this.getClass()) {
//            return false;
//        }
//        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy
//                ? proxy.getHibernateLazyInitializer().getPersistentClass()
//                : o.getClass();
//        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy
//                ? proxy.getHibernateLazyInitializer().getPersistentClass()
//                : this.getClass();
//        if (thisEffectiveClass != oEffectiveClass) {
//            return false;
//        }
//        final League that = (League) o;
//        return getId() != null && Objects.equals(getId(), that.getId());
//    }
//
//    @Override
//    public final int hashCode() {
//        return this instanceof HibernateProxy proxy
//                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
//                : getClass().hashCode();
//    }
//}
