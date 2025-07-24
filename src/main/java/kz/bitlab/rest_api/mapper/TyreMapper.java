package kz.bitlab.rest_api.mapper;

import jakarta.persistence.MapsId;
import kz.bitlab.rest_api.dto.TyreCreateDTO;
import kz.bitlab.rest_api.dto.TyreListDTO;
import kz.bitlab.rest_api.entity.Tyre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TyreMapper {
    TyreListDTO toTyreList(Tyre tyre);

    List<TyreListDTO> toTyreList(List<Tyre> tyreList);

    @Mapping(source = "names", target = "name")
    Tyre toTyre(TyreCreateDTO dto);
}
