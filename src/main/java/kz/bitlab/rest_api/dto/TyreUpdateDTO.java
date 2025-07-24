package kz.bitlab.rest_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TyreUpdateDTO {
    private Long id;
    private String name;
    private String profile;
    private Integer price;
    private String manufacturer;
    private Long countryId;
}
