package top.mnsx.store.dao;

import top.mnsx.store.entity.Cart;
import top.mnsx.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid 购物车数据id
     * @param num 更新的数量
     * @param modifiedUser 更新者
     * @param modifiedTime 更新时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id和商品的id来查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 商品数据
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    List<CartVO> findByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCids(Integer[] cids);
}
