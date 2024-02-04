package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.AgencyDao;
import java12.entity.Address;
import java12.entity.Agency;

import java.util.ArrayList;
import java.util.List;

public class AgencyDaoImpl implements AgencyDao {

    EntityManagerFactory entityManagerFactory = Config.getEntityManagerFactory();

    @Override
    public String save(Agency agency, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(agency);
            entityManager.persist(address);
            agency.setAddress(address);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        } finally {
            entityManager.close();
        }

        return "saved agency and address";
    }

    @Override
    public Agency getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;

        try {
            entityManager.getTransaction().begin();

            agency = entityManager.find(Agency.class, id);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return agency;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        try {
            entityManager.getTransaction().begin();

            agency = entityManager.find(Agency.class, id);
            entityManager.remove(agency);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        System.out.println("deleted " + agency.getName());
    }

    @Override
    public List<Agency> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Agency> agencies = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            agencies = entityManager.createQuery("select a from  Agency a").getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return agencies;
    }

    @Override
    public void update(Long id, Agency agency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency oldAgency = null;

        try {
            entityManager.getTransaction().begin();

            oldAgency = entityManager.find(Agency.class, id);
            if (oldAgency != null) {
                oldAgency.setName(agency.getName());
                oldAgency.setPhoneNumber(agency.getPhoneNumber());
            }else {
                System.out.println("Not found ID");
            }
            entityManager.merge(oldAgency);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        System.out.println("updated to " + agency.getName());;
    }

}
