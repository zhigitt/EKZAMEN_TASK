package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java12.config.Config;
import java12.dao.HouseDao;
import java12.entity.*;

import java.util.ArrayList;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    EntityManagerFactory entityManagerFactory = Config.getEntityManagerFactory();

    @Override
    public String save(House house, Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;
        RentInfo rentInfo = new RentInfo();
        try {
            entityManager.getTransaction().begin();

            owner = entityManager.find(Owner.class, ownerId);
            if (owner != null) {
                entityManager.persist(house);
                house.setOwner(owner);
                house.setRentInfo(rentInfo);
                rentInfo.setOwner(owner);
                rentInfo.setHouse(house);

                entityManager.persist(house);
                entityManager.persist(rentInfo);
            }else {
                return "Not found owner!";
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return  "saved" + house.getHouseType();
    }

    @Override
    public String saveHouse(House house) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;
        RentInfo rentInfo = new RentInfo();
        try {
            entityManager.getTransaction().begin();

                entityManager.persist(house);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return  "saved" + house.getHouseType();
    }

    @Override
    public House getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;

        try {
            entityManager.getTransaction().begin();

            house = entityManager.find(House.class, id);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return  house;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;
        try {
            entityManager.getTransaction().begin();

            house = entityManager.find(House.class, id);

            if (house != null) {
                Query rentInfoQuery = entityManager.createQuery("select r from RentInfo r where r.house.id = :houseId");
                rentInfoQuery.setParameter("houseId", id);
                List<RentInfo> rentInfoList = rentInfoQuery.getResultList();

                if (!rentInfoList.isEmpty()) {
                    for (RentInfo rentInfo : rentInfoList) {
                        entityManager.remove(rentInfo);
                    }
                }
                entityManager.remove(house);
                System.out.println("Deleted " + house.getHouseType());
            } else {
                System.out.println("House with ID " + id + " not found.");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
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
                oldHouse.setRating(house.getRating());
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

        System.out.println("updated ");
    }

    @Override
    public List<House> getHousesByRegion(String region) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<House> houses = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            List addresses = entityManager.createQuery("select a from Address  a where a.region =:par")
                    .setParameter("par", region)
                    .getResultList();

            for (Object address : addresses) {
//                houses.addAll(addresses.get)
            }//////////////////////////////////////////////////

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        return houses;
    }

    @Override
    public List<House> getHousesByAgency(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<House> houses = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            List<RentInfo> rentInfos = entityManager.createQuery("select r from RentInfo r where r.agency.id = :par", RentInfo.class)
                    .setParameter("par", agencyId)
                    .getResultList();

            for (RentInfo rentInfo : rentInfos) {
                houses.add(rentInfo.getHouse());
            }

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
    public List<House> getHousesByOwner(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<House> houses = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            List<RentInfo> rentInfos = entityManager.createQuery("select r from RentInfo r where r.owner.id = :par", RentInfo.class)
                    .setParameter("par", ownerId)
                    .getResultList();

            for (RentInfo rentInfo : rentInfos) {
                houses.add(rentInfo.getHouse());
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        return houses;
    }
}
