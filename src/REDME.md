MVC

REST

Model View Controller

RestFullStyle
[GET
POST
PUT
DELETE
PATCH]

//POSTMAN.   //INSOMNIA

[] - List, Massiv
{} - Object

http://localhost:8080/v1/tyres/3?name=Mishlen

100-199
200-299

//DTO Theme
//DTO - Data Transfer Object

package kz.bitlab.rest_api.mapper;

import kz.bitlab.rest_api.entity.TyreCategory;
import kz.bitlab.rest_api.repositories.TyreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MapperHelper {
private final TyreCategoryRepository tyreCategoryRepository;

    @Named("mapToCategories")
    public List<TyreCategory> mapToCategory(List<Long> ids) {
        List<TyreCategory> tyreCategories=new ArrayList<>();
        for(Long id: ids){
            tyreCategories.add(tyreCategoryRepository.findById(id).orElse(null));
        }
        return tyreCategories;
    }
}
