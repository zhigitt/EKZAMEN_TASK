package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.CustomerDao;
import java12.entity.Agency;
import java12.entity.Customer;
import java12.entity.House;
import java12.entity.RentInfo;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    EntityManagerFactory entityManagerFactory = Config.getEntityManagerFactory();

    @Override
    public String save(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(customer);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        } finally {
            entityManager.close();
        }

        return "saved customer";
    }

    @Override
    public Customer getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;

        try {
            entityManager.getTransaction().begin();

            customer = entityManager.find(Customer.class, id);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return customer;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
            entityManager.getTransaction().begin();

            customer = entityManager.find(Customer.class, id);
            entityManager.remove(customer);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        System.out.println("deleted " + customer.getFirstName());
    }

    @Override
    public List<Customer> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            customers = entityManager.createQuery("select c from  Customer c").getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return customers;

    }

    @Override
    public void update(Long id, Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer oldCustomer = null;

        try {
            entityManager.getTransaction().begin();

            oldCustomer = entityManager.find(Customer.class, id);
            if (oldCustomer != null) {
                oldCustomer.setFirstName(customer.getFirstName());
                oldCustomer.setLastName(customer.getLastName());
                oldCustomer.setEmail(customer.getEmail());
                oldCustomer.setGender(customer.getGender());
                oldCustomer.setNationality(customer.getNationality());
                oldCustomer.setDateOfBirth(customer.getDateOfBirth());
                oldCustomer.setFamilyStatus(customer.getFamilyStatus());

            }else {
                System.out.println("Not found ID");
            }
            entityManager.merge(oldCustomer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        System.out.println("updated to " + customer.getFirstName());
    }

    @Override
    public void saveCustomerWithHouse(Customer customer, House house, RentInfo rentInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<RentInfo> rentInfos = new ArrayList<>();
        rentInfos.add(rentInfo);

        try {
            entityManager.getTransaction().begin();

            customer.setRentInfos(rentInfos);
            rentInfo.setCustomer(customer);
            house.setRentInfo(rentInfo);
            rentInfo.setHouse(house);

            entityManager.persist(customer);
            entityManager.persist(house);
            entityManager.persist(rentInfo);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        System.out.println("saved customer");
    }

    @Override
    public void assignCustomerRent(Long cusId, Long housId, Long agencyId, RentInfo rentInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Customer customer = entityManager.find(Customer.class, cusId);
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            House house = entityManager.find(House.class, housId);
            if (house == null) {
                System.out.println("House not found.");
                return;
            }

            Agency agency = entityManager.find(Agency.class, agencyId);
            if (agency == null) {
                System.out.println("Agency not found.");
                return;
            }

            customer.getRentInfos().add(rentInfo);
            house.setRentInfo(rentInfo);
            agency.getRentInfos().add(rentInfo);
            rentInfo.setHouse(house);
            rentInfo.setAgency(agency);

            entityManager.persist(rentInfo);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }


    }

    @Override
    public void rentHouse(Long cusId, RentInfo rentInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<RentInfo> rentInfos = new ArrayList<>();
        rentInfos.add(rentInfo);

        try {
            entityManager.getTransaction().begin();



            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        System.out.println("saved customer");
    }
}
