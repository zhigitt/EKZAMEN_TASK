package java12.service;

import java12.entity.Address;
import java12.entity.Agency;

import java.util.List;

public interface AgencyService {
    String save(Agency agency, Address address);
    Agency getById(Long id);
    void deleteById(Long id);
    List<Agency> getAll();
    void update(Long id, Agency agency);

}
