package com.example.f1fantasy.mapper;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.entity.Driver;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverDTO toDTO(Driver entity);

    Driver toEntity(DriverDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(DriverDTO driverDTO, @MappingTarget Driver driver);
}
