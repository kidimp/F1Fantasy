//package com.example.f1fantasy.repository;
//
//import com.example.f1fantasy.model.entity.Driver;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface DriverRepository extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {
//
//    Optional<Driver> findByDriverNumberAndSeason_Year(Integer driverNumber, Integer seasonYear);
//}
