package kz.bitlab.rest_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tyres")
@AllArgsConstructor
@NoArgsConstructor
public class Tyre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "profile")
    private String profile;

    @Column(name = "price")
    private int price;

    @Column(name = "manufacturer")
    private String manufacturer;

}
