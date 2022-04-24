package top.mnsx.store.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.Address;
import top.mnsx.store.entity.Cart;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void testInsert() {
        Cart cart = new Cart();
        cart.setUid(10);
        cart.setPid(10000006);
        cart.setPrice(1000L);
        cart.setNum(2);
        cartMapper.insert(cart);
    }

    @Test
    public void testUpdateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "mnsx", new Date());
    }

    @Test
    public void testFindByUidAndPid() {
        System.out.println(cartMapper.findByUidAndPid(10, 10000006));
    }

    @Test
    public void testFindByUid() {
        System.out.println(cartMapper.findByUid(10));
    }
}
