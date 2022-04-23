package top.mnsx.store.service;

import top.mnsx.store.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findHostList();

    Product findById(Integer id);
}
