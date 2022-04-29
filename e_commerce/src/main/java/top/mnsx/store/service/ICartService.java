package top.mnsx.store.service;

import top.mnsx.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    void addToCart(Integer uid, Integer pid, Integer num, String username);

    List<CartVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCids(Integer uid, Integer[] cids);
}
