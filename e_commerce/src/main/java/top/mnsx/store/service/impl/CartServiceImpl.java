package top.mnsx.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.store.dao.CartMapper;
import top.mnsx.store.dao.ProductMapper;
import top.mnsx.store.entity.Cart;
import top.mnsx.store.entity.Product;
import top.mnsx.store.service.ICartService;
import top.mnsx.store.service.ex.AccessDeniedException;
import top.mnsx.store.service.ex.CartNotFoundException;
import top.mnsx.store.service.ex.InsertException;
import top.mnsx.store.service.ex.UpdateException;
import top.mnsx.store.vo.CartVO;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
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

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);

        if (result == null) {
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }

        Integer num = result.getNum() + 1;

        Date now = new Date();

        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }

        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);

        list.removeIf(cart -> !cart.getUid().equals(uid));
        return list;
    }
}
