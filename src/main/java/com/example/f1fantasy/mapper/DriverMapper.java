package com.example.f1fantasy.mapper;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.entity.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverDTO toDTO(Driver entity);
}
