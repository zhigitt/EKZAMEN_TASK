package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java12.config.Config;
import java12.dao.OwnerDao;
import java12.entity.*;

import java.time.LocalDate;
import java.time.Period;
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        houses.add(house);

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(owner);
            entityManager.persist(house);

            owner.setHouses(houses);
            house.setOwner(owner);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        return  "saved " + owner.getFirstName();
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Agency> agencies = new ArrayList<>();
        List<Owner> owners = new ArrayList<>();
        Agency agency = null;
        Owner owner = null;
        agencies.add(agency);
        owners.add(owner);

        try {
            entityManager.getTransaction().begin();

            owner = entityManager.find(Owner.class, ownerId);
            agency = entityManager.find(Agency.class, agencyId);
            RentInfo rentInfo = new RentInfo();
        if (owner != null && agency != null){
                owner.getAgencies().add(agency);
                agency.getOwners().add(owner);

                rentInfo.setAgency(agency);;
                rentInfo.setOwner(owner);

                entityManager.persist(rentInfo);
                entityManager.getTransaction().commit();
        }else {
            return "Not found agency or agency";
        }

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }

        return  "assign owner to agency";
    }

    @Override
    public void getOwnerByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        List<Owner> owners = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            agency = entityManager.find(Agency.class, agencyId);
            owners = agency.getOwners();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }

        System.out.println(owners);
    }

    @Override
    public void getOwnersNameAge() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Object[]> ownerDetailsList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();

            ownerDetailsList = entityManager.createQuery(
                            "select o.firstName, o.dateOfBirth from Owner o", Object[].class)
                    .getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        for (Object[] ownerDetails : ownerDetailsList) {
            String firstName = (String) ownerDetails[0];
            LocalDate dateOfBirth = (LocalDate) ownerDetails[1];
            System.out.println("Owner name: " + firstName + ", age: " + calculateAge(dateOfBirth));
        }
    }

    private int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }

        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
