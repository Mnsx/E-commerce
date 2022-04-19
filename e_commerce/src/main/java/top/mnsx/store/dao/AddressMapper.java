package top.mnsx.store.dao;

import top.mnsx.store.entity.Address;

public interface AddressMapper {
    /**
     * 插入数据的收获地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id同级收货地址数量
     * @param uid 收货地址数据
     * @return 当前用户的收货地址数
     */
    Integer countByUid(Integer uid);
}
