package kz.bitlab.rest_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tyres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tyre extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile")
    private String profile;

    @Column(name = "price")
    private Integer price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
}
