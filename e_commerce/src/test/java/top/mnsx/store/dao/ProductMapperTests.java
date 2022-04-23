package top.mnsx.store.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.Product;

import java.util.List;

@SpringBootTest
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList() {
        List<Product> list = productMapper.findHotList();
        System.out.println("count = " + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findById() {
        Integer id = 10000017;
        Product result = productMapper.findById(id);
        System.out.println(result);
    }
}
