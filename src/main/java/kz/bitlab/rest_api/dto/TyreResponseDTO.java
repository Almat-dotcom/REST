package kz.bitlab.rest_api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TyreResponseDTO {
    private Long id;
    private String name;
    private String profile;
    private int price;
    private String manufacturer;
}
