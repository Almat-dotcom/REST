package kz.bitlab.rest_api.mapper;

import jakarta.persistence.MapsId;
import kz.bitlab.rest_api.dto.TyreCreateDTO;
import kz.bitlab.rest_api.dto.TyreCreateResultDTO;
import kz.bitlab.rest_api.dto.TyreFullDTO;
import kz.bitlab.rest_api.dto.TyreListDTO;
import kz.bitlab.rest_api.entity.Tyre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class,
                CountryMapper.class})
public interface TyreMapper {
    TyreListDTO toTyreList(Tyre tyre);

    List<TyreListDTO> toTyreList(List<Tyre> tyreList);

    @Mapping(source = "names", target = "name")
    @Mapping(source = "countryId", target = "country", qualifiedByName = "toCountry")
    Tyre toTyre(TyreCreateDTO dto);

    TyreCreateResultDTO toCreateDTO(Tyre tyre);

    TyreFullDTO toFullDTO(Tyre tyre);
}
