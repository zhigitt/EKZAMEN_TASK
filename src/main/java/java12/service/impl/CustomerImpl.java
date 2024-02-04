package java12.service.impl;

import java12.dao.CustomerDao;
import java12.dao.impl.CustomerDaoImpl;
import java12.entity.Customer;
import java12.entity.RentInfo;
import java12.service.CustomerService;

import java.util.List;

public class CustomerImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public String save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public void update(Long id, Customer customer) {
        customerDao.update(id, customer);
    }

    @Override
    public void saveCustomerWithHouse(Customer customer, RentInfo rentInfo) {
        customerDao.saveCustomerWithHouse(customer, rentInfo);
    }

    @Override
    public void rentHouse(Long cusId, RentInfo rentInfo) {
        customerDao.rentHouse(cusId, rentInfo);
    }
}
