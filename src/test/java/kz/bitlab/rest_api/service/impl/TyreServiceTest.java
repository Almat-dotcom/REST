package kz.bitlab.rest_api.service.impl;

import kz.bitlab.rest_api.dto.*;
import kz.bitlab.rest_api.entity.Country;
import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.exception.TyreNotFoundException;
import kz.bitlab.rest_api.mapper.*;
import kz.bitlab.rest_api.repository.CountryRepository;
import kz.bitlab.rest_api.repository.TyreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
//Spy-Mock
class TyreServiceTest {
    @Mock
    private TyreRepository tyreRepository;
    @Mock
    CountryRepository countryRepository;

    @Spy
    private CountryMapper countryMapper = new CountryMapperImpl();
    @Spy
    private TyreMapper tyreMapper = new TyreMapperImpl();

    private MapperHelper mapperHelper;

    @InjectMocks
    private TyreServiceImpl tyreService;

    private Country country;

    //Рефлексия
    @BeforeEach
    void init() throws NoSuchFieldException, IllegalAccessException {
        mapperHelper = new MapperHelper(countryRepository);

        inject(tyreMapper,"mapperHelper", mapperHelper);
        inject(tyreMapper,"countryMapper", countryMapper);


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

        Tyre saved = getTyre();

        given(countryRepository.findById(any())).willReturn(Optional.of(country));
        given(tyreRepository.save(any())).willReturn(saved);

        TyreCreateResultDTO result = tyreService.addTyre(createDTO);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getName()).isEqualTo("Pilot Sport 4");
        verify(tyreMapper, times(1)).toTyre(createDTO);
    }

    @Test
    void getTyreByIdTest() {
        Tyre tyre = getTyre();

        given(tyreRepository.findById(10L)).willReturn(Optional.of(tyre));

        TyreFullDTO result = tyreService.getTyre(10L);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getCountry().getName()).isEqualTo("Japan");
        verify(tyreRepository, times(1)).findById(10L);
    }

    @Test
    void getTyreNotFoundTest() {
        given(tyreRepository.findById(10L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> tyreService.getTyre(10L))
                .isInstanceOf(TyreNotFoundException.class)
                .hasMessage("Tyre Not Found");
    }

    @Test
    void getTyresTest() {
        List<Tyre> tyres = Arrays.asList(
                Tyre.builder().id(1L).name("First").price(100).build(),
                Tyre.builder().id(2L).name("Second").price(200).build()
        );

//        given(tyreRepository.findAll()).willReturn(tyres);
        when(tyreRepository.findAll()).thenReturn(tyres);

        List<TyreListDTO> result = tyreService.getTyres();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void updateTyreTest() {
        Tyre oldT = getTyre();
        Tyre newT = Tyre.builder().id(oldT.getId()).name("New Name").build();
        TyreUpdateDTO dto = TyreUpdateDTO.builder().build();

        given(tyreRepository.findById(oldT.getId())).willReturn(Optional.of(oldT));
        given(tyreRepository.save(any())).willReturn(newT);

        TyreFullDTO result = tyreService.updateTyre(10L, dto);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getName()).isEqualTo("New Name");
    }

    //TDD - Test Driven Development

    @Test
    void updateTyreFailTest() {
        Tyre oldT = getTyre();
        Tyre newT = Tyre.builder().id(oldT.getId()).name("New Name").build();
        TyreUpdateDTO dto = TyreUpdateDTO.builder().build();

        given(tyreRepository.findById(oldT.getId())).willReturn(Optional.empty());

        TyreFullDTO result = tyreService.updateTyre(10L, dto);

        assertThat(result).isNull();
    }

    @Test
    void deleteTyreTest() {
        doNothing().when(tyreRepository).deleteById(1L);

        boolean result = tyreService.deleteTyre(1L);

        assertThat(result).isTrue();
    }

    private static void inject(Object target, String filedName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field f = target.getClass().getDeclaredField(filedName);
        f.setAccessible(true);
        f.set(target, value);
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
