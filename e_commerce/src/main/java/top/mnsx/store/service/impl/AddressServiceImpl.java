package top.mnsx.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.mnsx.store.dao.AddressMapper;
import top.mnsx.store.entity.Address;
import top.mnsx.store.service.IAddressService;
import top.mnsx.store.service.ex.AddressCountLimitException;
import top.mnsx.store.service.ex.InsertException;

import java.util.Date;

/**
 * 新增收货地址的实现
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Address address, String username, Integer uid) {
        Integer result = addressMapper.countByUid(uid);
        if(result >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        address.setUid(uid);
        int isDefault = result == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setCreatedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if(rows != 1) {
            throw new InsertException("插入用户的收货地址产生未知异常");
        }
    }
}
