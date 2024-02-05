package java12.service;

import java12.entity.Customer;
import java12.entity.House;
import java12.entity.RentInfo;

import java.util.List;

public interface CustomerService {
    String save(Customer customer);
    Customer getById(Long id);
    void deleteById(Long id);
    List<Customer> getAll();
    void update(Long id, Customer customer);


    void saveCustomerWithHouse(Customer customer, House house, RentInfo rentInfo);
    void assignCustomerRent(Long cusId, Long housId, Long agencyId, RentInfo rentInfo);
    void rentHouse(Long cusId, RentInfo rentInfo);

}
