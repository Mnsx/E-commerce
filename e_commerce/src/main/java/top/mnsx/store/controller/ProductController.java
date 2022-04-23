package top.mnsx.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.store.entity.Product;
import top.mnsx.store.service.IProductService;
import top.mnsx.store.util.JsonResult;

import java.util.List;

import static top.mnsx.store.controller.BaseController.OK;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHostList();
        return new JsonResult<>(OK, data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.findById(id);
        return new JsonResult<Product>(OK, data);
    }
}
