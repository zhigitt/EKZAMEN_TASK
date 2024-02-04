package java12.service.impl;

import java12.dao.AddressDao;
import java12.dao.impl.AddressDaoImpl;
import java12.entity.Address;
import java12.entity.Agency;
import java12.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressImpl implements AddressService {

    AddressDao addressDao = new AddressDaoImpl();


    @Override
    public Address getById(Long id) {
        return addressDao.getById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressDao.getAll();
    }

    @Override
    public void update(Long id, Address address) {
        addressDao.update(id, address);
    }

    @Override
    public void getCountAgencyByCity(String city) {
        addressDao.getCountAgencyByCity(city);
    }


    @Override
    public Map<String, List<Address>> groupByRegion() {
        return addressDao.groupByRegion();
    }

    @Override
    public Map<Agency, Address> getAddressWithAgency() {
        return addressDao.getAddressWithAgency();
    }


}
