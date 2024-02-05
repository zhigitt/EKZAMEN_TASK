package java12.service.impl;

import java12.dao.OwnerDao;
import java12.dao.impl.OwnerDaoImpl;
import java12.entity.Agency;
import java12.entity.House;
import java12.entity.Owner;
import java12.service.OwnerService;

import java.util.List;

public class OwnerImpl implements OwnerService {

    OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public String saveOwner(Owner owner) {
        return ownerDao.saveOwner(owner);
    }

    @Override
    public Owner getById(Long id) {
        return ownerDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        ownerDao.deleteById(id);
    }

    @Override
    public List<Owner> getAll() {

        return ownerDao.getAll();
    }

    @Override
    public void update(Long id, Owner owner) {
        ownerDao.update(id, owner);
    }

    @Override
    public String saveOwnerWithHouse(Owner owner, House house) {

        return ownerDao.saveOwnerWithHouse(owner, house);
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {

        return ownerDao.assignOwnerToAgency(ownerId, agencyId);
    }

    @Override
    public void getOwnerByAgencyId(Long agencyId) {
        ownerDao.getOwnerByAgencyId(agencyId);
    }

    @Override
    public void getOwnersNameAge() {
        ownerDao.getOwnersNameAge();
    }
}
