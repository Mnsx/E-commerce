package top.mnsx.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.service.ex.ServiceException;

@SpringBootTest
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void testAddToCart() {
        try {
            Integer uid = 10;
            Integer pid = 10000005;
            Integer amount = 10;
            String username = "mnsx";
            cartService.addToCart(uid, pid, amount, username);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
