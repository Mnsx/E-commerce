package top.mnsx.store.service;

import org.springframework.stereotype.Service;
import top.mnsx.store.entity.Address;

public interface IAddressService {
    void addNewAddress(Address address, String username, Integer uid);
}
