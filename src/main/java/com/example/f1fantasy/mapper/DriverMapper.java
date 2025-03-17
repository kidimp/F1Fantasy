package com.example.f1fantasy.mapper;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;
import com.example.f1fantasy.model.entity.Driver;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverDTO toDTO(Driver entity);

    Driver toEntity(DriverDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(DriverDTO driverDTO, @MappingTarget Driver driver);

    List<Driver> toDriverList(List<DriverDTO> driverDTOList);

    @Mapping(source = "broadcastName", target = "broadcastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "nameAcronym", target = "nameAcronym")
    @Mapping(source = "countryCode", target = "countryCode")
    @Mapping(source = "driverNumber", target = "driverNumber")
    @Mapping(source = "headshotUrl", target = "headshotUrl")
    DriverDTO toDriverDTO(ExternalDriverDataDTO externalDriverData);

    List<DriverDTO> toDriverDTOList(List<ExternalDriverDataDTO> externalDriverDataList);
}
