package java12.dao;

import java12.entity.Address;
import java12.entity.Agency;
import java12.entity.House;

import java.util.List;
import java.util.Map;

public interface HouseDao {
    String save(House house, Long ownerId);
    String saveHouse(House house);
    House getById(Long id);
    void deleteById(Long id);
    List<House> getAll();
    void update(Long id, House house);

    List<House> getHousesByRegion(String region);
    List<House> getHousesByAgency(Long agencyId);
    List<House> getHousesByOwner(Long ownerId);




}
