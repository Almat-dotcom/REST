package kz.bitlab.rest_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TyreListDTO {
    private Long id;
    private String name;
    private Integer price;
}
