package kz.bitlab.rest_api.mapper;

import kz.bitlab.rest_api.dto.TyreRequestDTO;
import kz.bitlab.rest_api.dto.TyreResponseDTO;
import kz.bitlab.rest_api.entity.Tyre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring"
        , uses = {MapperHelper.class})
public interface TyreMapper {
    @Mapping(target = "name", source = "tyreName")
    TyreResponseDTO mapToDTO(Tyre tyre);

    @Mapping(target = "tyreName", source = "name")
    @Mapping(target = "categories", source = "categoriesId", qualifiedByName = "mapToCategories")
    Tyre mapToEntity(TyreRequestDTO dto);
}
