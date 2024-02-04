package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.OwnerDao;
import java12.entity.Agency;
import java12.entity.Customer;
import java12.entity.House;
import java12.entity.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    EntityManagerFactory entityManagerFactory = Config.getEntityManagerFactory();

    @Override
    public String saveOwner(Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(owner);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        } finally {
            entityManager.close();
        }

        return "saved owner";
    }

    @Override
    public Owner getById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;

        try {
            entityManager.getTransaction().begin();

            owner = entityManager.find(Owner.class, id);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return owner;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;
        try {
            entityManager.getTransaction().begin();

            owner = entityManager.find(Owner.class, id);
            entityManager.remove(owner);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        System.out.println("deleted " + owner.getFirstName());
    }

    @Override
    public List<Owner> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Owner> owners = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            owners = entityManager.createQuery("select o from  Owner o").getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return owners;

    }

    @Override
    public void update(Long id, Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner oldOwner = null;

        try {
            entityManager.getTransaction().begin();

            oldOwner = entityManager.find(Owner.class, id);
            if (oldOwner != null) {

                oldOwner.setFirstName(owner.getFirstName());
                oldOwner.setLastName(owner.getLastName());
                oldOwner.setEmail(owner.getEmail());
                oldOwner.setGender(owner.getGender());
                oldOwner.setDateOfBirth(owner.getDateOfBirth());

            }else {
                System.out.println("Not found ID");
            }
            entityManager.merge(oldOwner);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        System.out.println("updated to " + owner.getFirstName());
    }

    @Override
    public String saveOwnerWithHouse(Owner owner, House house) {

        return null;
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        return null;
    }

    @Override
    public void getOwnerByAgencyId(Long agencyId) {

    }

    @Override
    public void getOwnersNameAge() {

    }
}
