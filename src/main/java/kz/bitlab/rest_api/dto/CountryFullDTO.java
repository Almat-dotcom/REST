package kz.bitlab.rest_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryFullDTO {
    private Long id;
    private String name;
}
