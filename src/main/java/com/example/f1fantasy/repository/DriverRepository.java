package com.example.f1fantasy.repository;

import com.example.f1fantasy.model.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {

    boolean existsByFullName(String fullName);

    boolean existsByDriverNumber(Integer driverNumber);

    boolean existsByNameAcronym(String nameAcronym);

    List<Driver> findAllByFullNameIn(List<String> fullNames);

    Optional<Driver> findByFullName(String fullName);

    boolean existsByDriverNumberAndFullNameNot(Integer driverNumber, String fullName);

    boolean existsByNameAcronymAndFullNameNot(String nameAcronym, String fullName);

    List<Driver> findAllByDriverNumberInAndNameAcronymIn(List<Integer> collect, List<String> collect1);
}
