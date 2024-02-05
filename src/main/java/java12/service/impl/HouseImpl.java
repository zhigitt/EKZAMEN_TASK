package java12.service.impl;

import java12.dao.HouseDao;
import java12.dao.impl.HouseDaoImpl;
import java12.entity.House;
import java12.service.HouseService;

import java.util.List;

public class HouseImpl implements HouseService {
    HouseDao houseDao = new HouseDaoImpl();

    @Override
    public String save(House house, Long ownerId) {
        return houseDao.save(house, ownerId);
    }

    @Override
    public House getById(Long id) {
        return houseDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        houseDao.deleteById(id);
    }

    @Override
    public List<House> getAll() {
        return houseDao.getAll();
    }

    @Override
    public void update(Long id, House house) {
        houseDao.update(id, house);
    }

    @Override
    public List<House> getHousesByRegion(String region) {
        return houseDao.getHousesByRegion(region);
    }

    @Override
    public List<House> getHousesByAgency(Long agencyId) {
        return houseDao.getHousesByAgency(agencyId);
    }

    @Override
    public List<House> getHousesByOwner(Long ownerId) {
        return houseDao.getHousesByOwner(ownerId);
    }

    @Override
    public String saveHouse(House house) {
        return houseDao.saveHouse(house);
    }
}
