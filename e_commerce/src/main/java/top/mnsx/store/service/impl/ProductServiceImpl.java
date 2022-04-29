package top.mnsx.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.store.dao.ProductMapper;
import top.mnsx.store.entity.Product;
import top.mnsx.store.service.IProductService;
import top.mnsx.store.service.ex.ProductNotFoundException;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHostList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if(product == null) {
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }

        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }
}
