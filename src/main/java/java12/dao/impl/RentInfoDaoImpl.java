package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Enumerated;
import java12.config.Config;
import java12.dao.RentInfoDao;
import java12.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;

public class RentInfoDaoImpl implements RentInfoDao {

    EntityManagerFactory entityManagerFactory = Config.getEntityManagerFactory();

    @Override
    public void getData(LocalDate start, LocalDate end) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            List<RentInfo> rentInfoList = entityManager.createQuery(
                            "select r from RentInfo r where :startDate <= r.checkOut and :endDate >= r.checkin",
                            RentInfo.class)
                    .setParameter("startDate", start)
                    .setParameter("endDate", end)
                    .getResultList();

            for (RentInfo rentInfo : rentInfoList) {
                entityManager.remove(rentInfo);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void getCountRentByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Long rentCount = (Long) entityManager.createQuery(
                            "select count (r) from RentInfo r " +
                                    "where r.agency.id = :agencyId " +
                                    "and :currentDate ")
                    .setParameter("agencyId", agencyId)
                    .setParameter("currentDate", LocalDate.now())
                    .getSingleResult();

            entityManager.getTransaction().commit();

            System.out.println(rentCount != null ? rentCount : 0L);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println(0L);
        } finally {
            entityManager.close();
        }
    }
}
