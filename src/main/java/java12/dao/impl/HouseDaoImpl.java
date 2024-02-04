package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.HouseDao;
import java12.entity.Customer;
import java12.entity.House;
import java12.entity.Owner;

import java.util.ArrayList;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    EntityManagerFactory entityManagerFactory = Config.getEntityManagerFactory();

    @Override
    public String save(House house, Long ownerId) {

        return null;
    }

    @Override
    public House getById(Long id) {

        return null;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;
        try {
            entityManager.getTransaction().begin();

            house = entityManager.find(House.class, id);
            entityManager.remove(house);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        System.out.println("deleted " + house.getHouse());
    }

    @Override
    public List<House> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses  = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            houses = entityManager.createQuery("select h from  House  h").getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;

    }

    @Override
    public void update(Long id, House house) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House oldHouse = null;

        try {
            entityManager.getTransaction().begin();

            oldHouse = entityManager.find(House.class, id);
            if (oldHouse != null) {

                oldHouse.setHouseType(house.getHouseType());
                oldHouse.setFurniture(house.isFurniture());
                oldHouse.setRoom(house.getRoom());

            }else {
                System.out.println("Not found ID");
            }
            entityManager.merge(oldHouse);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        System.out.println("updated to " + house.getHouse());
    }

    @Override
    public List<House> getHousesByRegion(String region) {
        return null;
    }

    @Override
    public List<House> getHousesByAgency(Long agencyId) {
        return null;
    }

    @Override
    public List<House> getHousesByOwner(Long ownerId) {
        return null;
    }
}
