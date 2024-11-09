//package com.example.f1fantasy.model.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.proxy.HibernateProxy;
//
//import java.time.OffsetDateTime;
//import java.util.Objects;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "race_weekend")
//public class RaceWeekend {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "circuit_key")
//    private Integer circuitKey;
//
//    @Column(name = "circuit_short_name")
//    private String circuitShortName;
//
//    @Column(name = "country_code", length = 3)
//    private String countryCode;
//
//    @Column(name = "country_key")
//    private Integer countryKey;
//
//    @Column(name = "country_name")
//    private String countryName;
//
//    @Column(name = "date_start")
//    private OffsetDateTime dateStart;
//
//    @Column(name = "gmt_offset")
//    private String gmtOffset;
//
//    @Column(name = "location")
//    private String location;
//
//    /**
//     * race weekend and meeting are the same things
//     */
//    @Column(name = "meeting_key")
//    private Integer meetingKey;
//
//    @Column(name = "meeting_name")
//    private String meetingName;
//
//    @Column(name = "meeting_official_name")
//    private String meetingOfficialName;
//
//    @Column(name = "year")
//    private Integer year;
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
//        final RaceWeekend that = (RaceWeekend) o;
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
