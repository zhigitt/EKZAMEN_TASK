package java12.dao;

import java.time.LocalDate;

public interface RentInfoDao {
    void getData(LocalDate start, LocalDate end);

    void getCountRentByAgencyId(Long agencyId);
}
