package java12.service.impl;

import java12.dao.AgencyDao;
import java12.dao.impl.AgencyDaoImpl;
import java12.entity.Address;
import java12.entity.Agency;
import java12.service.AgencyService;

import java.util.List;

public class AgencyImpl implements AgencyService {

    AgencyDao agencyDao = new AgencyDaoImpl();
    @Override
    public String save(Agency agency, Address address) {
        return agencyDao.save(agency, address);
    }

    @Override
    public Agency getById(Long id) {
        return agencyDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        agencyDao.deleteById(id);
    }

    @Override
    public List<Agency> getAll() {
        return agencyDao.getAll();
    }

    @Override
    public void update(Long id, Agency agency) {
        agencyDao.update(id, agency);
    }
}
