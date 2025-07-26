package kz.bitlab.rest_api.mapper;

import kz.bitlab.rest_api.dto.CountryFullDTO;
import kz.bitlab.rest_api.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryFullDTO toFullDTO(Country country);
}
