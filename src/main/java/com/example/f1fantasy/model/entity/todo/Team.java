//package com.example.f1fantasy.model.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "team")
//public class Team {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // Уникальный идентификатор команды
//
//    @Column(name = "code", nullable = false, unique = true)
//    private String code; // Уникальный код команды
//
//    @Column(name = "name", nullable = false)
//    private String name; // Название команды
//
//    @ManyToOne
//    @JoinColumn(name = "player_id", nullable = false)
//    private Player player; // Игрок, которому принадлежит команда
//
//    @ManyToMany
//    @JoinTable(
//            name = "team_drivers",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "driver_id")
//    )
//    @ToString.Exclude
//    private Set<Driver> drivers; // Гонщики команды (максимум 5)
//
//    @ManyToMany
//    @JoinTable(
//            name = "team_constructors",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "constructor_id")
//    )
//    @ToString.Exclude
//    private Set<Constructor> constructors; // Конструкторы команды (максимум 2)
//
//    @Column(name = "cost_cap", nullable = false)
//    private double costCap; // Лимит расходов команды (не более $100 млн)
//
//    // Ограничения
//    public static final int MAX_DRIVERS = 5; // Максимум 5 гонщиков
//    public static final int MAX_CONSTRUCTORS = 2; // Максимум 2 конструктора
//    public static final double MAX_COST_CAP = 100_000_000; // Максимум $100 млн
//}
