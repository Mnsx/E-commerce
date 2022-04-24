package top.mnsx.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.store.dao.CartMapper;
import top.mnsx.store.dao.ProductMapper;
import top.mnsx.store.entity.Cart;
import top.mnsx.store.entity.Product;
import top.mnsx.store.service.ICartService;
import top.mnsx.store.service.ex.InsertException;
import top.mnsx.store.service.ex.UpdateException;
import top.mnsx.store.vo.CartVO;

import java.util.Date;
import java.util.List;

@Service
public class ICartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer num, String username) {
        Cart cart = cartMapper.findByUidAndPid(uid, pid);
        if(cart == null) {
            cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(num);
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreatedTime(new Date());
            cart.setModifiedTime(new Date());
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            Integer rows = cartMapper.insert(cart);
            if(rows != 1) {
                throw new InsertException("将商品加入购物车时产生异常");
            }
        } else {
            int rows = cartMapper.updateNumByCid(cart.getCid(), num, username, new Date());
            if (rows != 1) {
                throw new UpdateException("更新购物车数据时产生异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findByUid(uid);
    }
}
