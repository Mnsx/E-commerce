package top.mnsx.store.service;

import org.springframework.stereotype.Service;
import top.mnsx.store.entity.Address;

import java.util.List;

public interface IAddressService {

    void addNewAddress(Address address, String username, Integer uid);

    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid, Integer uid, String username);

    void delete(Integer aid, Integer uid, String username);

    Address getByAid(Integer aid);

    void updateAddressByAid(Address address, String username);

    Address getByAid(Integer aid, Integer uid);
}
