package java12.service;

import java.time.LocalDate;

public interface RentInfoService {
    void getData(LocalDate start, LocalDate end);

    void getCountRentByAgencyId(Long agencyId);
}
