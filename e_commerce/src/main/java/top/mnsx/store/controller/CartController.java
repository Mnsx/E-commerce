package top.mnsx.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.store.service.ICartService;
import top.mnsx.store.util.JsonResult;
import top.mnsx.store.vo.CartVO;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid, pid, amount, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> data = cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        Integer data = cartService.addNum(cid, uid, username);
        return new JsonResult<Integer>(OK, data);
    }

    @GetMapping("list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        Integer uid = getuidFromSession(session);

        List<CartVO> data = cartService.getVOByCids(uid, cids);

        return new JsonResult<>(OK, data);
    }
}
