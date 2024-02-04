package java12.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor

@SequenceGenerator(name = "base_id_gen", sequenceName = "agency_seq", allocationSize = 1)
public class Agency extends BaseEntity{
    @Column(name = "full_name")
    private String name;

    @Column(name = "phone_number", length = 13)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Address address;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private RentInfo rentInfo;

    @ManyToMany
    private List<Owner> owners;

    @OneToMany
    private List<RentInfo> rentInfos;


    public Agency(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
