package kz.bitlab.rest_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class TyreRequestDTO {
    private String name;
    private String profile;
    private int price;
    private String manufacturer;
    private List<Long> categoriesId;
}
