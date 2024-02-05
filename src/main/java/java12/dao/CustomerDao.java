package java12.dao;

import java12.entity.*;

import java.util.List;

public interface CustomerDao {
    String save(Customer customer);
    Customer getById(Long id);
    void deleteById(Long id);
    List<Customer> getAll();
    void update(Long id, Customer customer);


    void saveCustomerWithHouse(Customer customer, House house, RentInfo rentInfo);

    void assignCustomerRent(Long cusId, Long housId, Long agencyId, RentInfo rentInfo);

    void rentHouse(Long cusId, RentInfo rentInfo);


}
