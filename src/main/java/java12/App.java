package java12;

import java12.config.Config;
import java12.entity.*;
import java12.entity.enums.FamilyStatus;
import java12.entity.enums.Gender;
import java12.entity.enums.HouseType;
import java12.service.*;
import java12.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Config.getEntityManagerFactory();

        AgencyService agencyService = new AgencyImpl();
        AddressService addressService = new AddressImpl();
        CustomerService customerService = new CustomerImpl();
        OwnerService ownerService = new OwnerImpl();
        HouseService houseService = new HouseImpl();

        //AGENCY
//        System.out.println(agencyService.save(new Agency("Agent007", "+996702636545"), new Address("Bishkek", "Chui", "Tokotogula")));
//        System.out.println(agencyService.getById(2L));
//        System.out.println(agencyService.getAll());
//        agencyService.deleteById(4L);
//        agencyService.update(10L, new Agency("Batken", "+996701854525") );


        //ADDRESS
//        System.out.println(addressService.getAll());
//        System.out.println(addressService.getAddressWithAgency());
//        addressService.update(5L, new Address("osh", "dadas", "asdasd"));

//        addressService.getCountAgencyByCity("Bishkek");
//        System.out.println(addressService.groupByRegion());



        //CUSTOMER
//        System.out.println(customerService.save(new Customer(
//                "Mirlan", "Arsatnabekov",
//                "mirlan@gmail.com",
//                LocalDate.of(2002, 01, 02),
//                Gender.MALE,
//                "kyrgyz",
//                FamilyStatus.SINGLE)));

//        customerService.update(9L, new Customer("Aliaskar", "Temirbekov", "ali@gmail.com",
//                LocalDate.of(2002, 01, 02),
//                Gender.MALE,
//                "kyrgyz",
//                FamilyStatus.SINGLE));


//        customerService.deleteById(10L);
//        System.out.println(customerService.getAll());

//        customerService.saveCustomerWithHouse((new Customer("aa", "aa", "aaa@gmail.com", LocalDate.of(2001, 01, 10),
//                Gender.FEMALE, "aa", FamilyStatus.SINGLE)),
//                new House(HouseType.PENT_HOUSE,
//                        BigDecimal.valueOf(13132), 12.4, 5, true),
//                new RentInfo(LocalDate.of(2022,01,01),
//                        LocalDate.of(2024,01,01)));
//
//        customerService.assignCustomerRent(1L, 4L, 2L, new RentInfo(LocalDate.of(2022,01,01),
//                        LocalDate.of(2024,01,01)));


        //OWNER
//        System.out.println(ownerService.saveOwner(new Owner("Urmat", "Taichikov", "zhigit@gmail.com", LocalDate.of(2004,01,05), Gender.MALE)));

//        ownerService.saveOwnerWithHouse(new Owner("Myrzaiym", "Keldibekova", "myrzaiym@gmail.com", LocalDate.of(2003, 01, 02), Gender.FEMALE),
//                new House(HouseType.PENT_HOUSE, BigDecimal.valueOf(1212), 12.2, 5, true));

//        System.out.println(ownerService.assignOwnerToAgency(1L, 1L));

//        ownerService.getOwnerByAgencyId(1L);

//        ownerService.getOwnersNameAge();



        //HOUSE
//        System.out.println(houseService.save(new House(HouseType.PENT_HOUSE, BigDecimal.valueOf(20000), 4.5, 5, false), 1L));
//        System.out.println(houseService.saveHouse(new House(HouseType.PENT_HOUSE, BigDecimal.valueOf(20000), 4.5, 5, false)));

//        System.out.println(houseService.getById(2L));

//        houseService.deleteById(3L);

//        System.out.println(houseService.getAll());

//        houseService.update(4L, new House(HouseType.APARTMENT, BigDecimal.valueOf(33000), 5.5, 10, true));

//        System.out.println(houseService.getHousesByAgency(1L));
//        System.out.println(houseService.getHousesByOwner(1L));


    }
}
