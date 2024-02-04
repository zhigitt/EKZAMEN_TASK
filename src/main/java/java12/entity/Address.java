package java12.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "address_seq", allocationSize = 1)
public class Address  extends BaseEntity{
    private String city;
    private String region;
    private String street;

    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;
    }
    @OneToOne(mappedBy = "address")
    private Agency agency;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
