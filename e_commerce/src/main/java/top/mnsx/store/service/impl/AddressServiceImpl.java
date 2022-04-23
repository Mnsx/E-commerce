package top.mnsx.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.mnsx.store.dao.AddressMapper;
import top.mnsx.store.dao.DistrictMapper;
import top.mnsx.store.entity.Address;
import top.mnsx.store.service.IAddressService;
import top.mnsx.store.service.IDistrictService;
import top.mnsx.store.service.ex.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 新增收货地址的实现
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Address address, String username, Integer uid) {
        Integer result = addressMapper.countByUid(uid);
        if(result >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        int isDefault = result == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if(rows != 1) {
            throw new InsertException("插入用户的收货地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        List<Address> result = new ArrayList<>();
        for(Address address : list) {
            Address newAddress = new Address();
            newAddress.setUid(address.getUid());
            newAddress.setAid(address.getAid());
            newAddress.setName(address.getName());
            newAddress.setProvinceName(address.getProvinceName());
            newAddress.setCityName(address.getCityName());
            newAddress.setAreaName(address.getAreaName());
            newAddress.setZip(address.getZip());
            newAddress.setAddress(address.getAddress());
            newAddress.setPhone(address.getPhone());
            newAddress.setTag(address.getTag());
            result.add(newAddress);
        }
        return result;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址找不到");
        }

        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if(rows == null) {
            throw new DeleteException("删除数据产生未知的异常");
        }

        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            return;
        }

        if (result.getIsDefault() == 1) {
            Address address = addressMapper.findLastModified(uid);
            rows = addressMapper.updateDefaultByAid(
                    address.getAid(),
                    username,
                    new Date());
            if (rows != 1) {
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }

    }

    @Override
    public Address getByAid(Integer aid) {
        Address result = addressMapper.findByAid(aid);

        if(result == null){
            throw new AddressNotFoundException("尝试访问的收货地址不存在");
        }

        result.setUid(null);
        result.setIsDefault(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);

        return result;
    }
}
