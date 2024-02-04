package java12.dao;

import java12.entity.Address;
import java12.entity.Agency;
import java12.entity.House;
import java12.entity.Owner;

import java.util.List;

public interface OwnerDao {
    String saveOwner(Owner owner);
    Owner getById(Long id);
    void deleteById(Long id);
    List<Owner> getAll();
    void update(Long id, Owner owner);

    String saveOwnerWithHouse(Owner owner, House house);
    String assignOwnerToAgency(Long ownerId, Long agencyId);

    void getOwnerByAgencyId(Long agencyId);

    void getOwnersNameAge();
}
