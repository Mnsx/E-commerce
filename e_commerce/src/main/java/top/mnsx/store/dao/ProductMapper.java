package top.mnsx.store.dao;

import org.springframework.stereotype.Repository;
import top.mnsx.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findHotList();

    Product findById(Integer id);
}
