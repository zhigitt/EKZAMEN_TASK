package java12;

import java12.config.Config;
import java12.entity.Address;
import java12.entity.Agency;
import java12.entity.Customer;
import java12.entity.Owner;
import java12.entity.enums.FamilyStatus;
import java12.entity.enums.Gender;
import java12.service.*;
import java12.service.impl.*;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Config.getEntityManagerFactory();

        AgencyService agencyService = new AgencyImpl();
        AddressService addressService = new AddressImpl();
        CustomerService customerService = new CustomerImpl();
        OwnerService ownerService = new OwnerImpl();
        HouseService houseService = new HouseImpl();

        //AGENCY
        System.out.println(agencyService.save(new Agency("Naryn", "+996702636545"), new Address("Bishkek", "Chui", "Tokotogula")));
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


        //OWNER
        System.out.println(ownerService.saveOwner(new Owner("Urmat", "Taichikov", "zhigit@gmail.com", LocalDate.of(2004,01,05), Gender.MALE)));


    }
}
