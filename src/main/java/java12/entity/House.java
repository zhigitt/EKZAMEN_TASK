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
    private  RentInfo rentInfo;

    @OneToOne(cascade = CascadeType.REMOVE)
    private  Address address;

    private BigDecimal price;
    private double rating;
    private int room;
    private boolean furniture;


    public House(HouseType houseType,  BigDecimal price, double rating, int room, boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.room = room;
        this.furniture = furniture;
    }

    @Override
    public String toString() {
        return "House{" +
                "houseType=" + houseType +
                ", price=" + price +
                ", rating=" + rating +
                ", room=" + room +
                ", furniture=" + furniture +
                '}';
    }
}
