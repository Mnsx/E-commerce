package top.mnsx.store.service;

import top.mnsx.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    void addToCart(Integer uid, Integer pid, Integer num, String username);

    List<CartVO> getVOByUid(Integer uid);
}
