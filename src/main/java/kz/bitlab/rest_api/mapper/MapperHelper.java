package kz.bitlab.rest_api.mapper;

import kz.bitlab.rest_api.entity.Country;
import kz.bitlab.rest_api.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperHelper {
    private final CountryRepository countryRepository;

    @Named("toCountry")
    public Country qwerqww(Long countryId) {
        Country country = countryRepository.findById(countryId).orElse(null);
        if (country == null) return null;
        return country;
    }
}
