package java12.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rent_info")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "rent_info_seq", allocationSize = 1)
public class RentInfo extends BaseEntity{
    @Column(name = "check_in")
    private LocalDate checkin;
    @ManyToOne
    private Customer customer;

    @Column(name = "check_out")
    private LocalDate checkOut;

    public RentInfo(LocalDate checkin, LocalDate checkOut) {
        this.checkin = checkin;
        this.checkOut = checkOut;
    }

    @ManyToOne
    private Owner owner;


    @OneToOne
    private  House house;

    @ManyToOne
    private Agency agency;




}
