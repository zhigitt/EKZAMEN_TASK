package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java12.config.Config;
import java12.dao.AddressDao;
import java12.entity.Address;
import java12.entity.Agency;
import java12.entity.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDaoImpl implements AddressDao {

    EntityManagerFactory entityManagerFactory = Config.getEntityManagerFactory();

    @Override
    public Address getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Address address = null;

        try {
            entityManager.getTransaction().begin();

            address = entityManager.find(Address.class, id);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return address;
    }

    @Override
    public List<Address> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addresses  = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            addresses = entityManager.createQuery("select a from  Address a").getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return addresses;
    }

    @Override
    public void update(Long id, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Address oldAddress = null;

        try {
            entityManager.getTransaction().begin();

            oldAddress = entityManager.find(Address.class, id);
            if (oldAddress != null) {
                oldAddress.setAgency(address.getAgency());
                oldAddress.setCity(address.getCity());
                oldAddress.setRegion(address.getRegion());
                oldAddress.setStreet(address.getStreet());
            }else {
                System.out.println("Not found ID");
            }
            entityManager.merge(oldAddress);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        System.out.println("updated to " + address.getCity());;
    }

    @Override
    public void getCountAgencyByCity(String city) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Long count = 0L;
            try {
                entityManager.getTransaction().begin();
                count = entityManager.createQuery("select count (aa.id) from Agency aa inner join" +
                                " Address a on aa.address.id = a.id where a.city in(:parCity)",Long.class)
                        .setParameter("parCity",city).getSingleResult();

                entityManager.getTransaction().commit();
            } catch (Exception e) {
                if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                System.err.println(e.getMessage());
            } finally {
                entityManager.close();
            }
        System.out.println(city + " count agency : " + count);
    }


    @Override
    public Map<String, List<Address>> groupByRegion() {

        List<Address> addresses = new ArrayList<>();
        Map<String, List<Address>> listMap = new HashMap<>();

        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            addresses = entityManager.createQuery("SELECT a FROM Address a", Address.class)
                    .getResultList();
            for (Address address : addresses) {
                String region = address.getRegion();
                listMap.computeIfAbsent(region, k -> new ArrayList<>()).add(address);
            }

            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            entityManagerFactory.close();
        }
        return listMap;
    }

    @Override
    public Map<Agency, Address> getAddressWithAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<Agency, Address> addressMap = new HashMap<>();
        List<Address> addresses = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            addresses = entityManager.createQuery("select a from  Address a").getResultList();

            for (Address address : addresses) {
                addressMap.put(address.getAgency(),address);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return addressMap;
    }
}
