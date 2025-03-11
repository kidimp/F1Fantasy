//package com.example.f1fantasy.model.entity;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.proxy.HibernateProxy;
//
//import java.util.Objects;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "player")
//public class Player {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull
//    @Size(max = 256)
//    @Column(name = "player_code", nullable = false, unique = true)
//    private String playerCode;
//
//    @Column(name = "player_name", nullable = false)
//    private String playerName;
//
//    @Column(name = "team_limit", nullable = false)
//    private int teamLimit; // Максимальное количество команд == 3
//
//    @ToString.Exclude
//    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Team> teams;
//
//    @ManyToMany
//    @JoinTable(
//            name = "player_leagues",
//            joinColumns = @JoinColumn(name = "player_id"),
//            inverseJoinColumns = @JoinColumn(name = "league_id")
//    )
//    private Set<League> leagues; // Лиги, в которых участвует игрок
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
//        final Player that = (Player) o;
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