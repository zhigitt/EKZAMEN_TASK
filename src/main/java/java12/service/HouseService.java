package java12.service;

import java12.entity.House;

import java.util.List;

public interface HouseService {
    String save(House house, Long ownerId);
    House getById(Long id);
    void deleteById(Long id);
    List<House> getAll();
    void update(Long id, House house);

    List<House> getHousesByRegion(String region);
    List<House> getHousesByAgency(Long agencyId);
    List<House> getHousesByOwner(Long ownerId);
    String saveHouse(House house);

}
