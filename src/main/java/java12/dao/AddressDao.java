package java12.dao;

import java12.entity.Address;
import java12.entity.Agency;

import java.util.List;
import java.util.Map;

public interface AddressDao {
    Address getById(Long id);
    List<Address> getAll();
    void update(Long id, Address address);
    void getCountAgencyByCity(String city);
    Map<String, List<Address>> groupByRegion();

    Map<Agency, Address> getAddressWithAgency();

}
