package top.mnsx.store.dao;

import org.apache.ibatis.annotations.Param;
import top.mnsx.store.entity.Address;
import top.mnsx.store.entity.District;

import java.util.Date;
import java.util.List;

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

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid 收货地址id
     * @return 收货地址，如果没有找到则返回null值
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid值来修改用户的收货地址设置为非默认
     * @param uid 用户id
     * @return 受影响行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 设置指定收货地址为用户的默认地址
     * @param aid 收货地址
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 返回受影响的行数
     */
    Integer updateDefaultByAid(@Param("aid")Integer aid, @Param("modifiedUser")String modifiedUser, @Param("modifiedTime")Date modifiedTime);

    /**
     * 通过收货地址Id删除地址数据
     * @param aid 收货地址id
     * @return 返回受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户id查询当前用户最后一次被修改的收货地址数据
     * @param uid 用户id
     * @return 收货地址
     */
    Address findLastModified(Integer uid);

    Integer updateAddressByAid(@Param("address")Address address);
}
