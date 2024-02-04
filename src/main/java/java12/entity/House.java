package java12.entity;

import jakarta.persistence.*;
import java12.entity.enums.HouseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "house_seq", allocationSize = 1)
public class House extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "house_type", columnDefinition = "VARCHAR(10)")
    private HouseType houseType;

    @ManyToOne
    private Owner owner;

    @OneToOne
    private  House house;


    private BigDecimal price;
    private double rating;
    private int room;
    private boolean furniture;
}
