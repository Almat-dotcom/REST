package kz.bitlab.rest_api.dto;

import jakarta.persistence.*;
import kz.bitlab.rest_api.entity.Country;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TyreFullDTO {
    private Long id;
    private String name;
    private String profile;
    private Integer price;
    private String manufacturer;
    private CountryFullDTO country;
}
