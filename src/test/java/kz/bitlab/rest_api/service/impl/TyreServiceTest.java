package kz.bitlab.rest_api.service.impl;

import kz.bitlab.rest_api.dto.CountryFullDTO;
import kz.bitlab.rest_api.dto.TyreCreateDTO;
import kz.bitlab.rest_api.dto.TyreCreateResultDTO;
import kz.bitlab.rest_api.dto.TyreFullDTO;
import kz.bitlab.rest_api.entity.Country;
import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.exception.TyreNotFoundException;
import kz.bitlab.rest_api.mapper.TyreMapper;
import kz.bitlab.rest_api.repository.CountryRepository;
import kz.bitlab.rest_api.repository.TyreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class TyreServiceTest {
    @Mock
    private TyreRepository tyreRepository;

    @Mock
    private TyreMapper tyreMapper;

    @InjectMocks
    private TyreServiceImpl tyreService;

    private Country country;

    @BeforeEach
    void init() {
        country = Country.builder().id(2L).name("Japan").build();
    }

    @Test
    void addTyreTest() {
        TyreCreateDTO createDTO = TyreCreateDTO.builder()
                .names("Pilot Sport 4")
                .profile("R17")
                .price(150000)
                .manufacturer("Michelin")
                .countryId(2L)
                .build();

        TyreCreateResultDTO resultDTO = TyreCreateResultDTO.builder()
                .id(10L)
                .name("Pilot Sport 4")
                .build();

        Tyre saved = getTyre();

        given(tyreMapper.toTyre(any())).willReturn(saved);
        given(tyreRepository.save(any())).willReturn(saved);
        given(tyreMapper.toCreateDTO(any())).willReturn(resultDTO);

        TyreCreateResultDTO result = tyreService.addTyre(createDTO);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getName()).isEqualTo("Pilot Sport 4");
    }

    @Test
    void getTyreByIdTest() {
        Tyre tyre = getTyre();

        TyreFullDTO fullDto = TyreFullDTO.builder()
                .id(10L)
                .name("Pilot Sport 4")
                .profile("R17")
                .price(150000)
                .manufacturer("Michelin")
                .country(new CountryFullDTO(1L, "Japan"))
                .build();

        given(tyreRepository.findById(10L)).willReturn(Optional.of(tyre));
        given(tyreMapper.toFullDTO(any())).willReturn(fullDto);

        TyreFullDTO result = tyreService.getTyre(10L);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getCountry().getName()).isEqualTo("Japan");
    }

    @Test
    void getTyreNotFoundTest() {
        given(tyreRepository.findById(10L)).willReturn(Optional.empty());

        assertThatThrownBy(()->tyreService.getTyre(10L))
                .isInstanceOf(TyreNotFoundException.class)
                .hasMessage("Tyre Not Found");
    }

    private Tyre getTyre() {
        return Tyre.builder()
                .id(10L)
                .name("Pilot Sport 4")
                .profile("R17")
                .price(150000)
                .manufacturer("Michelin")
                .country(country)
                .build();
    }
}
