package java12.service.impl;

import java12.dao.RentInfoDao;
import java12.dao.impl.RentInfoDaoImpl;
import java12.service.RentInfoService;

import java.time.LocalDate;

public class RentInfoImpl implements RentInfoService {

    RentInfoDao rentInfoDao = new RentInfoDaoImpl();

    @Override
    public void getData(LocalDate start, LocalDate end) {
        rentInfoDao.getData(start, end);
    }

    @Override
    public void getCountRentByAgencyId(Long agencyId) {
        rentInfoDao.getCountRentByAgencyId(agencyId);
    }
}
