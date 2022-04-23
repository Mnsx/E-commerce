package top.mnsx.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.Product;
import top.mnsx.store.service.ex.ServiceException;

import java.util.List;

@SpringBootTest
public class ProductServiceTests {
    @Autowired
    private IProductService productService;

    @Test
    public void testFindHotList() {
        List<Product> hostList = productService.findHostList();
        hostList.forEach(System.out::println);
    }

    @Test
    public void findById() {
        try {
            Integer id = 100000179;
            Product result = productService.findById(id);
            System.out.println(result);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
