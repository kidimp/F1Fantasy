package com.example.f1fantasy.repository.specification;

import com.example.f1fantasy.model.entity.Driver;
import org.springframework.data.jpa.domain.Specification;

/**
 * A class for creating JPA specifications for the {@link Driver} entity.
 * Used for dynamically building queries with various criteria.
 */
public class DriverSpecification {

    private DriverSpecification() {
    }

    /**
     * Specification for filtering drivers by driver ID.
     */
    public static Specification<Driver> hasDriverId(Long driverId) {
        return (root, query, builder) -> {
            if (driverId == null) {
                return builder.conjunction(); // No condition
            }
            return builder.equal(root.get("driverId"), driverId);
        };
    }

    /**
     * Specification for filtering drivers by broadcast name.
     */
    public static Specification<Driver> hasBroadcastName(String broadcastName) {
        return (root, query, builder) -> {
            if (broadcastName == null || broadcastName.isEmpty()) {
                return builder.conjunction(); // No condition
            }
            return builder.equal(root.get("broadcastName"), broadcastName);
        };
    }

    /**
     * Specification for filtering drivers by first name.
     */
    public static Specification<Driver> hasFirstName(String firstName) {
        return (root, query, builder) -> {
            if (firstName == null || firstName.isEmpty()) {
                return builder.conjunction(); // No condition
            }
            return builder.like(builder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        };
    }

    /**
     * Specification for filtering drivers by last name.
     */
    public static Specification<Driver> hasLastName(String lastName) {
        return (root, query, builder) -> {
            if (lastName == null || lastName.isEmpty()) {
                return builder.conjunction(); // No condition
            }
            return builder.like(builder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
        };
    }

    /**
     * Specification for filtering drivers by full name.
     */
    public static Specification<Driver> hasFullName(String fullName) {
        return (root, query, builder) -> {
            if (fullName == null || fullName.isEmpty()) {
                return builder.conjunction(); // No condition
            }
            return builder.like(builder.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%");
        };
    }

    /**
     * Specification for filtering drivers by name acronym.
     */
    public static Specification<Driver> hasNameAcronym(String nameAcronym) {
        return (root, query, builder) -> {
            if (nameAcronym == null || nameAcronym.isEmpty()) {
                return builder.conjunction(); // No condition
            }
            return builder.equal(root.get("nameAcronym"), nameAcronym);
        };
    }

    /**
     * Specification for filtering drivers by country code.
     */
    public static Specification<Driver> hasCountryCode(String countryCode) {
        return (root, query, builder) -> {
            if (countryCode == null || countryCode.isEmpty()) {
                return builder.conjunction(); // No condition
            }
            return builder.equal(root.get("countryCode"), countryCode);
        };
    }

    /**
     * Specification for filtering drivers by driver number.
     */
    public static Specification<Driver> hasDriverNumber(Integer driverNumber) {
        return (root, query, builder) -> {
            if (driverNumber == null) {
                return builder.conjunction(); // No condition
            }
            return builder.equal(root.get("driverNumber"), driverNumber);
        };
    }

    /**
     * Combine all specifications into one.
     */
    public static Specification<Driver> buildSpecification(Long driverId, String broadcastName, String firstName,
                                                           String lastName, String fullName, String nameAcronym,
                                                           String countryCode, Integer driverNumber) {
        Specification<Driver> spec = Specification.where(null);

        if (driverId != null) {
            spec = spec.and(hasDriverId(driverId));
        }
        if (broadcastName != null && !broadcastName.isEmpty()) {
            spec = spec.and(hasBroadcastName(broadcastName));
        }
        if (firstName != null && !firstName.isEmpty()) {
            spec = spec.and(hasFirstName(firstName));
        }
        if (lastName != null && !lastName.isEmpty()) {
            spec = spec.and(hasLastName(lastName));
        }
        if (fullName != null && !fullName.isEmpty()) {
            spec = spec.and(hasFullName(fullName));
        }
        if (nameAcronym != null && !nameAcronym.isEmpty()) {
            spec = spec.and(hasNameAcronym(nameAcronym));
        }
        if (countryCode != null && !countryCode.isEmpty()) {
            spec = spec.and(hasCountryCode(countryCode));
        }
        if (driverNumber != null) {
            spec = spec.and(hasDriverNumber(driverNumber));
        }
        return spec;
    }
}
