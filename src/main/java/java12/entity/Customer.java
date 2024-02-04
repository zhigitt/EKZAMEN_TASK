package java12.entity;

import jakarta.persistence.*;
import java12.entity.enums.FamilyStatus;
import java12.entity.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "customer_seq", allocationSize = 1)
public class Customer extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "family_status", columnDefinition = "VARCHAR(10)")
    private FamilyStatus familyStatus;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<RentInfo> rentInfos;



    public Customer(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, String nationality, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                ", familyStatus=" + familyStatus +
                '}';
    }
}
