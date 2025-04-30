package kz.bitlab.rest_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Column(name = "tyre_name")
    private String tyreName;

    @Column(name = "profile")
    private String profile;

    @Column(name = "price")
    private int price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tyre_and_categories",
            joinColumns = @JoinColumn(name = "tyre_id"),
            inverseJoinColumns = @JoinColumn(name = "tyre_category_id")
    )
    private List<TyreCategory> categories;
}
